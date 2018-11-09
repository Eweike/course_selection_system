$(document).ready(function(e) {
	$("#goOtherCourse").click(function() {
		goOtherCourse();
	})
	$("#goThisCourse").click(function() {
		goThisCourse();
	});
	console.log("开始")
	getTable();
});

function goOtherCourse() {
	$("#thisCourse").hide(500);
	$("#otherCourse").show(500);
	$("#goThisCourse").removeAttr("disabled");
	$("#goOtherCourse").attr("disabled", true);
	$("#bread-last").html("历史课程");
};

function goThisCourse() {
	$("#otherCourse").hide(500);
	$("#thisCourse").show(500);
	$("#goOtherCourse").removeAttr("disabled");
	$("#goThisCourse").attr("disabled", true);
	$("#bread-last").html("本学期课程");
};

function getTable() {
	$(function() {
		$('#tb_scores')
				.bootstrapTable(
						{
							url : "http://localhost:8080/sun5790/curriculumList",
							dataType : "json",/* 数据类型 */
							pagination : true,/* 是否分页 */
							search : true, // 是否显示表格搜索
							columns : [
									{
										title : '序号',/**/
										field : 'CId',/* Json数据对应的字段 */
										align : 'center',
										formatter : function(value, row, index) {
											return index + 1;
										}

									},
									{
										title : '课程名',
										field : 'CName',
										align : 'center'
									},
									{
										title : '学分',
										field : 'CCredit',
										align : 'center'
									},
									{
										title : '课程类别',
										field : 'CType',
										align : 'center'
									},
									{
										title : '课程性质',
										field : 'CNature',
										align : 'center'
									},
									{
										title : '开课单位',
										field : 'CInstitute',
										align : 'center'
									},
									{
										title : '学生人数',
										field : 'CSelectedNumb',
										align : 'center'
									},
									{
										title : '操作',
										field : 'CId',
										align : 'center',
										formatter : function(value, row, index) {
											var l = '<a href="test.jsp?cid='
													+ row.CId
													+ '">课程详情</a>&nbsp ';
											var e = '<a href="javascript:goCourseDetail('
													+ row.CId + ');">学生成绩</a> ';
											return l + e;
										}
									} ],
						});
	});
}
function goCourseDetail(id) {
	$.ajax({
		url : "http://localhost:8080/shf5790_1/courseStudentsList?cid=" + id,
		type : "GET",// 方法类型
		async : true,
		success : function(res) {
			console.log(res)
			var studentList = res;
			var state = 2;	//0表示未结课，1表示已结课有成绩，2表示已结课无成绩
			for (var i = 0; i < studentList.length; i++) {
				if (studentList[i].eiCprogress == "已结课"
						&& studentList[i].scId != null) {
					state = 1;
				}else if(studentList[i].eiCprogress == "授课中"){
					state = 0;
				}
			}
			console.log(state)
			if (state == 1) {
				window.location.href = "score-manager.jsp?cid=" + id;
			} else if(state == 2){
				layer.confirm('暂无成绩，是否添加？', {
					btn : [ '添加', '暂不' ],
					offset : [ '180px', '650px' ]
				// 按钮
				}, function() {
					layer.closeAll();
					window.location.href = "addScore.jsp?cid=" + id;
				});
			}else{
				layer.msg("该课程暂未结束", {
					offset : [ '180px', '800px' ],
					anim : 6
				});
			}
		},
		error : function(data) {
			layer.msg("操作异常：" + data + "   " + data.responseText, {
				icon : 5
			});
		}
	});
}