package swtech.pageDesignControl.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import swtech.pageDesignControl.entity.Flow;
import swtech.pageDesignControl.entity.OnbusInessFlow;
import swtech.pageDesignControl.entity.ServeFlow;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class FlowOnbusIness implements Serializable {
    private OnbusInessFlow onbusInessFlow;
    private Flow leave;
    private ServeFlow serveFlow;
    public  FlowOnbusIness(){

    }
}
