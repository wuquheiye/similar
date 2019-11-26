package swtech.pageDesignControl.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swtech.pageDesignControl.entity.Flow;
import swtech.pageDesignControl.entity.OnbusInessFlow;
import swtech.pageDesignControl.entity.ServeFlow;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class FlowOnbusIness implements Serializable {
    private OnbusInessFlow onbusInessFlow;
    private Flow leave;
    private ServeFlow serveFlow;
}
