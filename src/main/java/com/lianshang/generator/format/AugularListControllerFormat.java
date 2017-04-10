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
            ss = ss+"  DTColumnBuilder.newColumn('"+columnMeta.getColumnName()+"').withTitle('"+columnMeta.getComment
                ()+"'), \n";
        }
        return ss;
    }
    private static String getAddJsFunction(TableMeta meta,String className,String uptableName, String tablepath,String tablemodelpath){
        String ss = "   $scope.add"+uptableName+" = function () {\n"
            + "      var opener = $scope\n"
            + "      var modalInstance;\n"
            + "      modalInstance = $uibModal.open({\n"
            + "        templateUrl: '"+tablemodelpath+"/add-"+className+".html',\n"
            + "        size: 'lg',\n"
            + "        controller: function ($scope, $uibModal, $uibModalInstance) {\n"
            + "          $scope.nextBtnDisabled = true;\n"
            + "          $scope.cancel = function () {\n"
            + "            $uibModalInstance.dismiss('cancel');\n"
            + "          };\n"
            + "\n"
            + "          $scope.ok = function () {\n"
            + "            "+className+"MgmtService.add"+uptableName+"($scope.modal)\n"
            + "            .then(function successCallback(response) {\n"
            + "              toaster.pop({\n"
            + "                type: response.data.code == 200 ? '新增成功' : '新增失败',\n"
            + "                title: '提示信息',\n"
            + "                body: response.data.message,\n"
            + "                showCloseButton: true,\n"
            + "                timeout: 5000\n"
            + "              });\n"
            + "              if (response.data.code == 200) {\n"
            + "                opener.dtInstance.reloadData();\n"


            + "                $uibModalInstance.close();\n"
            + "              }\n"
            + "            });\n"
            + "          };\n"
            + "\n"
            + "        },\n"
            + "        windowClass: 'animated bounceIn'\n"
            + "      });\n"

            + "    }";

        return ss;
    }
    public static String getFileContent(String className, String prefixClassPackage, TableMeta meta,
        String tablepath,String tablemodelpath) throws
        ServiceException {
        String tableName = Tools.lineToHump(meta.getTableName());
        String uptableName = Tools.upCaptureName(tableName);

        StringBuilder builder = new StringBuilder();
        builder.append("(function () {\n"
            + "\n"
            + "  "+className+"Ctrl.$inject = ['$scope', '$http', '$uibModal', '$compile',\n"
            + "    '$filter', 'toaster', '"+className+"MgmtService', 'DTOptionsBuilder',\n"
            + "    'DTColumnBuilder', '$state'];\n"
            + "\n"
            + "  function "+className+"Ctrl($scope, $http, $uibModal, $compile, $filter,\n"
            + "      toaster, "+tableName+"MgmtService, DTOptionsBuilder, DTColumnBuilder, $state) {\n"
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

            + "\n"
            + "      return "+className+"MgmtService.query"+uptableName+"List("
            + "         draw, sortColumn, sortDir, start, limit)\n"
            + "      .then(function (resp) {\n"
            + "        fnCallback(resp.data);\n"
            + "        vm.tableData = resp.data;\n"
            + "      }).finally(function () {\n"
            + "        $scope.queryBtnDisabled = false;\n"
            + "      });\n"
            + "\n"
            + "    }\n"
            + "\n"
            + "    function actionsHtml(data, type, full, meta) {\n"
            +" var str="
            +"      str += '<button ng-disabled=\"false\" class=\"btn btn-xs btn-blue btn-rounded\" "
            + "ng-click=\"edit"+uptableName+"('\n"
                + "          + '\\'' + data.id + '\\')\">' +\n"
                + "          '    <i class=\"fa fa-bolt\">编辑</i>' +\n"
                + "          '</button>&nbsp;&nbsp;';\n"
                + "                str += '<button ng-disabled=\"false\" class=\"btn btn-xs btn-blue btn-rounded\" "
            + "ng-click=\"view"+uptableName+"('\n"
                + "          + '\\'' + data.id + '\\')\">' +\n"
                + "          '    <i class=\"fa fa-bolt\">查看</i>' +\n"
                + "          '</button>&nbsp;&nbsp;';\n"


            + "                str += '<button ng-disabled=\"false\" class=\"btn btn-xs btn-blue btn-rounded\" "
            + "ng-click=\"delete"+uptableName+"('\n"
            + "          + '\\'' + data.id + '\\')\">' +\n"
            + "          '    <i class=\"fa fa-bolt\">删除</i>' +\n"
            + "          '</button>&nbsp;&nbsp;';\n"



                +"return str;"
                + "}"
                + "\n"


            + "    $scope.add"+uptableName+" = function () {\n"
            +"         alert('进入新增')"
            +getAddJsFunction( meta, className, uptableName,  tablepath, tablemodelpath)

            + "    }\n"
            + "\n"
            + "    $scope.edit"+uptableName+"  = function (id) {\n"
            +"         alert('进入编辑')"
            + "    }\n"
            + "\n"
            + "    $scope.view"+uptableName+"  = function (id) {\n"
            +"         alert('进入查看')"

            + "    }\n"
            + "\n"
            + "    $scope.delete"+uptableName+"  = function (id) {\n"
            +"         alert('进入删除')"

            + "    }\n"
            + "\n"

            + "\n"
            + "  }\n"
            + "\n"
            + "  angular\n"
            + "  .module('app."+className+"-mgmt')\n"
            + "  .controller('"+className+"Ctrl', "+className+"Ctrl);\n"
            + "\n"
            + "})();"


        );

        return builder.toString();


    }
}
