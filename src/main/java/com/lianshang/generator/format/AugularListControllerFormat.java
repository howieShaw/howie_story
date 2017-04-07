package com.lianshang.generator.format;

import com.lianshang.generator.constant.Constant;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.ColumnMeta;
import com.lianshang.generator.meta.TableMeta;
import com.lianshang.generator.util.Tools;
import java.util.List;

/**
 * Created by walker on 16/2/16.
 */
public class AugularListControllerFormat {
    private static String getColmun(TableMeta meta){
        String ss = "";
        for(ColumnMeta columnMeta:meta.getColumnMetas()){
            ss = ss+"  DTColumnBuilder.newColumn('"+columnMeta.getColumnName()+"').withTitle('"+columnMeta.getComment()+"') \n";
        }
        return ss;
    }
    public static String getFileContent(String className, String prefixClassPackage, TableMeta meta) throws ServiceException {
        String tableName = Tools.lineToHump(meta.getTableName());

        StringBuilder builder = new StringBuilder();
        builder.append("(function () {\n"
            + "\n"
            + "  "+className+"Ctrl.$inject = ['$scope', '$http', '$uibModal', '$compile',\n"
            + "    '$filter', 'toaster', 'activityMgmtService', 'DTOptionsBuilder',\n"
            + "    'DTColumnBuilder', '$state'];\n"
            + "\n"
            + "  function "+className+"Ctrl($scope, $http, $uibModal, $compile, $filter,\n"
            + "      toaster, activityMgmtService, DTOptionsBuilder, DTColumnBuilder, $state) {\n"
            + "\n"
            + "    var vm = this;\n"
            + "    var rootCtrlScope = $scope;\n"

            + "    $scope.dtInstance = {};\n"
            + "\n"
            + "    $scope.dtOptions = DTOptionsBuilder\n"
            + "    .fromSource('')\n"
            + "    .withFnServerData(serverData)\n"
            + "    .withDataProp('data')\n"
            + "    .withOption('serverSide', true)\n"
            + "    .withOption('searching', false)\n"
            + "    .withOption('lengthChange', true)\n"
            + "    .withOption('autoWidth', false)\n"
            + "    .withOption('scrollX', false)\n"
            + "    .withOption('lengthMenu', [10, 25, 50, 100])\n"
            + "    .withOption('displayLength', 10)\n"
            + "    .withPaginationType('full_numbers')\n"
            + "    .withOption('createdRow', function (row, data, dataIndex) {\n"
            + "      $compile(angular.element(row).contents())($scope);\n"
            + "    });\n"
            + "\n"
            + "    $scope.dtColumns = [\n"
            +getColmun(meta)
            + "      DTColumnBuilder.newColumn(null).withTitle('操作').notSortable().withClass(\n"
            + "          'text-center p-w-xxs-i')\n"
            + "      .withOption('width', ' 90px').renderWith(actionsHtml)\n"
            + "    ];\n"
            + "\n"
            + "    function serverData(sSource, aoData, fnCallback, oSettings) {\n"
            + "\n"
            + "      var draw = aoData[0].value;\n"
            + "      var sortColumn = $scope.dtColumns[parseInt(\n"
            + "          aoData[2].value[0].column)].mData;\n"
            + "      var sortDir = aoData[2].value[0].dir;\n"
            + "      var start = aoData[3].value;\n"
            + "      var limit = aoData[4].value;\n"
            + "\n"
            + "      if (vm.status == null || vm.status === '') {\n"
            + "        vm.status = -1;\n"
            + "      }\n"
            + "\n"
            + "      return activityMgmtService.queryActivityList(vm.title, vm.status,\n"
            + "          vm.reStartDate, vm.reEndDate, draw, sortColumn, sortDir, start, limit)\n"
            + "      .then(function (resp) {\n"
            + "        fnCallback(resp.data);\n"
            + "        vm.tableData = resp.data;\n"
            + "      }).finally(function () {\n"
            + "        $scope.queryBtnDisabled = false;\n"
            + "      });\n"
            + "\n"
            + "    }\n"
            + "\n"
            + "    function actionsHtml(data, type, full, meta) {\n"+
            "      str += '<button ng-disabled=\"true\" class=\"btn btn-xs btn-blue btn-rounded\" "
            + "ng-click=\"edit"+className+"('\n"
                + "          + '\\'' + data.id + '\\')\">' +\n"
                + "          '    <i class=\"fa fa-bolt\">编辑</i>' +\n"
                + "          '</button>&nbsp;&nbsp;';\n"
                + "                str += '<button ng-disabled=\"true\" class=\"btn btn-xs btn-blue btn-rounded\" ng-click=\"edit"+className+"('\n"
                + "          + '\\'' + data.id + '\\')\">' +\n"
                + "          '    <i class=\"fa fa-bolt\">查看</i>' +\n"
                + "          '</button>&nbsp;&nbsp;';\n"
                + "                str += '<button ng-disabled=\"true\" class=\"btn btn-xs btn-blue btn-rounded\" ng-click=\"edit"+className+"('\n"
                + "          + '\\'' + data.id + '\\')\">' +\n"
                + "          '    <i class=\"fa fa-bolt\">删除</i>' +\n"
                + "          '</button>&nbsp;&nbsp;';"
                +"return str;"
                + "}"
                + "/n"
            + "    $scope.add"+className+" = function () {\n"

            + "    }\n"
            + "\n"
            + "    $scope.edit\"+className+\" = function (id) {\n"

            + "    }\n"
            + "\n"


            + "\n"
            + "  }\n"
            + "\n"
            + "  angular\n"
            + "  .module('app.activity-mgmt')\n"
            + "  .controller('"+className+"Ctrl', "+className+"Ctrl);\n"
            + "\n"
            + "})();"


        );

        return builder.toString();


    }
}
