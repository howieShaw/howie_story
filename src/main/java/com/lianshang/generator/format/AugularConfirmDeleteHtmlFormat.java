package com.lianshang.generator.format;

import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.ColumnMeta;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.Tools;

/**
 * Created by walker on 16/2/16.
 */
public class AugularConfirmDeleteHtmlFormat {
    public static String getFileContent(String className, String prefixClassPackage, TableMeta meta) throws ServiceException {
        String tableName = Tools.lineToHump(meta.getTableName());
        String upTableName = Tools.upCaptureName(tableName);
        StringBuilder builder = new StringBuilder();
        builder.append(
            "<div class=\"modal-dialog no-margins\">\n"
                + "    <div class=\"modal-header\">\n"
                + "        <h4 class=\"modal-title\">删除操作确认</h4>\n"
                + "    </div>\n"
                + "    <div class=\"modal-body\">\n"
                + "        <form class=\"form-horizontal\">\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"col-sm-12\">\n"
                + "                    <span>是否确认删除?</span>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </form>\n"
                + "    </div>\n"
                + "    <div class=\"modal-footer\">\n"
                + "        <button type=\"button\" class=\"btn btn-w-sm btn-white\" ng-click=\"cancel()\">取消</button>\n"
                + "        <button type=\"button\" class=\"btn btn-w-sm btn-primary\" ng-click=\"ok()\" ng-disabled=\"okBtnDisabled\">确定</button>\n"
                + "    </div>\n"
                + "</div>"
        );

        return builder.toString();

    }
}
