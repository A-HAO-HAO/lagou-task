<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>简历列表</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script>
        function loadData(){
            $.ajax({
                url: '/resume/find-all',
                type: 'GET',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    if(data.success){
                        $("#data-list").empty();
                        var dataList = data.data;
                        var html = "";
                        for(var i=0; i<dataList.length; i++){
                            html+="<tr>";
                            html+="<td name='id'>"+dataList[i].id+"</td>";
                            html+="<td><input type='text' name='address' value='"+dataList[i].address+"'></td>";
                            html+="<td><input type='text' name='name' value='"+dataList[i].name+"'></td>";
                            html+="<td><input type='text' name='phone' value='"+dataList[i].phone+"'></td>";
                            html+="<td><button class='update-btn'>修改</button>&nbsp;&nbsp;<button class='delete-btn'>删除</button></td>";
                            html+="</tr>";
                        }
                        $("#data-list").append(html);

                        $(".update-btn").click(function () {
                            var $tr = $(this).parents("tr");
                            var id = $("td[name='id']",$tr).text();
                            var address = $("input[name='address']",$tr).val();
                            var name = $("input[name='name']",$tr).val();
                            var phone = $("input[name='phone']",$tr).val();
                            var data = {id:id,address:address,name:name,phone:phone};
                            $.ajax({
                                url: '/resume/update',
                                type: 'POST',
                                data:data,
                                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                                dataType: 'json',
                                success: function (data) {
                                    if(data.success){
                                        alert("修改成功");
                                        loadData();
                                    }else{
                                        alert("处理失败")
                                    }
                                }
                            })
                        });

                        $(".delete-btn").click(function () {
                            var $tr = $(this).parents("tr");
                            var id = $tr.find("td[name='id']").text();
                            var address = $tr.find("input[name='address']").val();
                            var name = $tr.find("input[name='name']").val();
                            var phone = $tr.find("input[name='phone']").val();
                            var data = {id:id,address:address,name:name,phone:phone};
                            $.ajax({
                                url: '/resume/delete',
                                type: 'POST',
                                data:data,
                                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                                dataType: 'json',
                                success: function (data) {
                                    if(data.success){
                                        alert("删除成功");
                                        loadData();
                                    }else{
                                        alert("处理失败")
                                    }
                                }
                            })
                        });

                        $("#add-btn").click(function () {
                            var $tr = $(this).parents("tr");
                            var address = $tr.find("input[name='address']").val();
                            var name = $tr.find("input[name='name']").val();
                            var phone = $tr.find("input[name='phone']").val();
                            var data = {address:address,name:name,phone:phone};
                            $.ajax({
                                url: '/resume/add',
                                type: 'POST',
                                data:data,
                                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                                dataType: 'json',
                                success: function (data) {
                                    if(data.success){
                                        alert("新增成功");
                                        $("#table-foot").find("input").val("");
                                        loadData();
                                    }else{
                                        alert("处理失败")
                                    }
                                }
                            })
                        });
                    }
                }
            })
        }

        $(function () {
            loadData();
        })
    </script>


    <style>
        div{
            padding:10px 10px 0 10px;
        }
    </style>
</head>
<body>
    <div>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>address</th>
                    <th>name</th>
                    <th>phone</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="data-list"></tbody>
            <tfoot id="table-foot">
                <tr>
                    <td></td>
                    <td><input type="text" name="address"> </td>
                    <td><input type="text" name="name"> </td>
                    <td><input type="text" name="phone"> </td>
                    <td><button id="add-btn">新增</button></td>
                </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>
