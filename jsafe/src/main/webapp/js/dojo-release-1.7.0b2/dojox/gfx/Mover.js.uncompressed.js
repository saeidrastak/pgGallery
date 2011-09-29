require.built();
/*
	Copyright (c) 2004-2011, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

define("dojox/gfx/Mover", ["dojo/_base/kernel", "dojo/_base/array", "dojo/_base/declare", "dojo/_base/connect"], function(dojo){
	return dojo.declare("dojox.gfx.Mover", null, {
		constructor: function(shape, e, host){
			// summary: an object, which makes a shape follow the mouse,
			//	used as a default mover, and as a base class for custom movers
			// shape: dojox.gfx.Shape: a shape object to be moved
			// e: Event: a mouse event, which started the move;
			//	only clientX and clientY properties are used
			// host: Object?: object which implements the functionality of the move,
			//	 and defines proper events (onMoveStart and onMoveStop)
			this.shape = shape;
			this.lastX = e.clientX
			this.lastY = e.clientY;
			var h = this.host = host, d = document,
				firstEvent = dojo.connect(d, "onmousemove", this, "onFirstMove");
			this.events = [
				dojo.connect(d, "onmousemove", this, "onMouseMove"),
				dojo.connect(d, "onmouseup",   this, "destroy"),
				// cancel text selection and text dragging
				dojo.connect(d, "ondragstart",   dojo, "stopEvent"),
				dojo.connect(d, "onselectstart", dojo, "stopEvent"),
				firstEvent
			];
			// notify that the move has started
			if(h && h.onMoveStart){
				h.onMoveStart(this);
			}
		},
		// mouse event processors
		onMouseMove: function(e){
			// summary: event processor for onmousemove
			// e: Event: mouse event
			var x = e.clientX;
			var y = e.clientY;
			this.host.onMove(this, {dx: x - this.lastX, dy: y - this.lastY});
			this.lastX = x;
			this.lastY = y;
			dojo.stopEvent(e);
		},
		// utilities
		onFirstMove: function(){
			// summary: it is meant to be called only once
			this.host.onFirstMove(this);
			dojo.disconnect(this.events.pop());
		},
		destroy: function(){
			// summary: stops the move, deletes all references, so the object can be garbage-collected
			dojo.forEach(this.events, dojo.disconnect);
			// undo global settings
			var h = this.host;
			if(h && h.onMoveStop){
				h.onMoveStop(this);
			}
			// destroy objects
			this.events = this.shape = null;
		}
	});
});