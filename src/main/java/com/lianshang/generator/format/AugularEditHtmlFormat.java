package com.lianshang.generator.format;

import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.ColumnMeta;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.Tools;

/**
 * Created by walker on 16/2/16.
 */
public class AugularEditHtmlFormat {
    private static String getColmun(TableMeta meta){
        String ss = "";
        for(ColumnMeta columnMeta:meta.getColumnMetas()){
            ss = ss+"               <label class=\"col-lg-4 control-label\">"+columnMeta.getComment()+"</label>\n"
                + "              <div class=\"col-lg-8\">\n"
                + "                <div class=\"input-icon right\">\n"
                + "                  <i class=\"pull-right\"></i>\n"
                + "                  <input type=\"text\" name=\"modal."+Tools.lineToHump( columnMeta.getColumnName()
            )+"\"  "
                + "class=\"form-control\"\n"
                + "                         ng-model=\"modal."+Tools.lineToHump( columnMeta.getColumnName())+"\" data-op=\"equal\" required>\n"
                + "                </div>\n"
                + "              </div>\n";
        }
        return ss;
    }

    public static String getFileContent(String className, String prefixClassPackage, TableMeta meta) throws ServiceException {
        String tableName = Tools.lineToHump(meta.getTableName());
        String upTableName = Tools.upCaptureName(tableName);
        StringBuilder builder = new StringBuilder();
        builder.append(
            "<div class=\"modal-dialog modal-lg no-margins\">\n"
                + "  <div class=\"modal-header\">\n"
                + "    <button type=\"button\" class=\"close\" ng-click=\"cancel()\" aria-label=\"Close\"><i class=\"fa fa-times\"></i></button>\n"
                + "    <h4 class=\"modal-title\">绑定活动渠道</h4>\n"
                + "  </div>\n"
                + "  <div class=\"ibox float-e-margins\">\n"
                + "    <div class=\"ibox-content m-b-sm border-bottom\">\n"
                + "      <form class=\"form-horizontal\" ng-submit=\"submit($event)\">\n"
                + "        <div class=\"row\">\n"
                + "          <div class=\"col-sm-4\">\n"
                + "            <div class=\"form-group\">\n"
                +               getColmun(meta)
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"

                + "        </div>\n"
                + "      </form>\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>"
        );

        return builder.toString();

    }
}
