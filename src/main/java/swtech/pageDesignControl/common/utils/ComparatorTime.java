package swtech.pageDesignControl.common.utils;


import swtech.pageDesignControl.entity.Journal;
import swtech.pageDesignControl.entity.UpdateProject;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class ComparatorTime implements Comparator {
    /**
     *
     * TODO 以对象Time判断两个list对象排序（可选）.
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Object arg0, Object arg1) {
        UpdateProject cb ;
        Journal rd ;
        UpdateProject cb1 ;
        Journal rd1 ;

        if(arg0 instanceof UpdateProject){
            cb=(UpdateProject)arg0;
            if(arg1 instanceof Journal){
                rd=(Journal)arg1;
                return cb.getUpTime().compareTo(rd.getJcreateTime());
            }else{
                cb1=(UpdateProject)arg1;
                return cb.getUpTime().compareTo(cb1.getUpTime());
            }
        }else{
            rd1=(Journal)arg0;

            if(arg1 instanceof Journal){
                rd=(Journal)arg1;
                return rd1.getJcreateTime().compareTo(rd.getJcreateTime());
            }else{
                cb=(UpdateProject)arg1;
                return rd1.getJcreateTime().compareTo(cb.getUpTime());
            }
        }
    }
}
