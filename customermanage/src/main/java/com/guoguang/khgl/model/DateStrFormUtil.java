package com.guoguang.khgl.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tk on 2015/12/1.
 */
public class DateStrFormUtil {

        public static String formatDateString(String srcstr) {
            // TODO Auto-generated method stub
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            String deststr = "";
            try {
                Date date = df.parse(srcstr);
                deststr = new SimpleDateFormat("yyyy/MM/dd").format(date);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return deststr;
        }


}
