package swtech.pageDesignControl.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-11-15
 */
@Data
public class RolesPermissions{

    private static final long serialVersionUID = 1L;

    private String permission;

    private String roleName;


}
