package pgGallery.db.pojo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Album extends UpdatablePojo{
	private BigDecimal id = null;

	@NotBlank(message = "Album name cannot be blank")
	@Length(min = 1, max = 100, message = "Album name must be between 1 and 100 characters")
	private String name = null;

	private BigDecimal parentId = null;
	
	@NotNull
	private boolean isPublic = false;
	
	/**
	 * The itemId of the item that should be used as the thumbnail.
	 */
	private BigDecimal defaultId = null;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getParentId() {
		return parentId;
	}

	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public BigDecimal getDefaultId() {
		return defaultId;
	}

	public void setDefaultId(BigDecimal defaultId) {
		this.defaultId = defaultId;
	}
}
