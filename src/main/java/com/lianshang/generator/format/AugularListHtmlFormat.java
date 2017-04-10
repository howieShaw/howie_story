package com.lianshang.generator.format;

import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.Tools;
import java.util.Random;

/**
 * Created by walker on 16/2/16.
 */
public class AugularListHtmlFormat {

    public static String getFileContent(String className, String prefixClassPackage, TableMeta meta) throws ServiceException {
        String tableName = Tools.lineToHump(meta.getTableName());
        String uptablename = Tools.upCaptureName(tableName);
        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"wrapper wrapper-content animated fadeInRight ng-scope\">");
        builder.append(Constant.RETURN);
        builder.append("  <div class=\"ibox-content m-b-sm border-bottom\">");
        builder.append(Constant.RETURN);
        builder.append("    <form class=\"form-horizontal\" ng-submit=\"submit()\">");
        builder.append(Constant.RETURN);
        builder.append("      <div class=\"row\">\n"
            + "        <div class=\"col-md-1\">\n"
            + "          <button class=\"btn btn-primary btn-w-sm \" ng-click=\"add"+uptablename+"()\" >新增\n"
            + "          </button>\n"
            + "        </div>\n"
            + "      </div>\n"
            + "\n"
            + "    </form>\n"
            + "  </div>\n"
            + "\n"
            + "  <div class=\"row\">\n"
            + "    <div class=\"col-lg-12\">\n"
            + "      <div class=\"ibox\">\n"
            + "\n"
            + "        <div class=\"ibox-content\">\n"
            + "          <form class=\"form-horizontal\" ng-submit=\"submit()\">\n"
            + "            <div class=\"row\">\n"
            + "              <div class=\"col-xs-12\">\n"
            + "                <div class=\"form-group\">\n"
            + "                  <div class=\"col-md-12\">\n"
            + "                    <table datatable=\"\" width=\"100%\" dt-options=\"dtOptions\" dt-columns=\"dtColumns\"\n"
            + "                           dt-instance=\"dtInstance\"\n"
            + "                           class=\"table table-striped table-bordered table-hover\" data-order='[[ 2, \"desc\" ]]'>\n"
            + "                    </table>\n"
            + "                  </div>\n"
            + "                </div>\n"
            + "              </div>\n"
            + "            </div>\n"
            + "            <div class=\"hr-line-dashed\"></div>\n"
            + "          </form>\n"
            + "        </div>\n"
            + "\n"
            + "      </div>\n"
            + "    </div>\n"
            + "  </div>\n"
            + "</div>");

        return builder.toString();

    }
}
