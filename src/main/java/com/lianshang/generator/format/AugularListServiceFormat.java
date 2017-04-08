package com.lianshang.generator.format;

import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.Tools;

/**
 * Created by walker on 16/2/16.
 */
public class AugularListServiceFormat {

    public static String getFileContent(String className, String prefixClassPackage, TableMeta meta) throws ServiceException {
        String tableName = Tools.lineToHump(meta.getTableName());
        String upTableName = Tools.upCaptureName(tableName);
        StringBuilder builder = new StringBuilder();
        builder.append(
            "'use strict';\n"
            + "\n"
            + className+"MgmtService.$inject = ['$http'];\n"
            + "\n"
            + "function "+className+"MgmtService($http) {\n"
            + "  return {\n"
            + "    query"+upTableName+"List: query"+upTableName+"List,\n"
            + "    add"+upTableName+": add"+upTableName+",\n"
            + "    update"+upTableName+": update"+upTableName+",\n"

            + "  };\n"
            + "\n"

            + "  function query"+upTableName+"List( draw,\n"
            + "      sortColumn, sortDir, start, limit) {\n"
            + "    return $http.post('/"+tableName+"-mgmt/query"+upTableName+"List.do', {\n"

            + "      draw: draw,\n"
            + "      sortColumn: sortColumn,\n"
            + "      sortDir: sortDir,\n"
            + "      start: start,\n"
            + "      limit: limit\n"
            + "    });\n"
            + "  }\n"
            + "\n"

            + "  function add"+upTableName+"(modal) {\n"
            + "    return $http.post('"+tableName+"-mgmt/add"+upTableName+".do', modal"
            + "    );\n"
            + "  }\n"
            + "\n"
            + "  function update"+upTableName+"(modal) {\n"
            + "    return $http.post('"+tableName+"-mgmt/update"+upTableName+".do',modal"
            + "    );\n"
            + "  }\n"

                + "  function query"+upTableName+"Detail(id) {\n"
                + "    return $http.post('"+tableName+"-mgmt/query"+upTableName+"Detail.do',{"
                + " id:id  "
                + "}"
                + "    );\n"
                + "  }\n"

                + "  function delete"+upTableName+"(id) {\n"
                + "    return $http.post('"+tableName+"-mgmt/delete"+upTableName+".do',{"
                + " id:id  "
                + "}"
                + "    );\n"
                + "  }\n"
            + "\n"
            + "\n"
            + "\n"
            + "}\n"
            + "\n"
            + "angular\n"
            + ".module('app."+className+"-mgmt')\n"
            + ".factory('"+className+"MgmtService', "+className+"MgmtService);\n");

        return builder.toString();

    }
}
