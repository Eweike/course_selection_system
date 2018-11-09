var id; // 当前课程的id，全局变量
$(document).ready(function(e) {
	getCId();
	$("#btn_query").click(function() {
		searchByCondition();
	})
	getTongji();
});
function inputScore(index, thisScore, thisScid, thisProcess) {
	if (thisProcess == "已结课") {
		var str = "<div class='input-group' id='inputScoreBox"
				+ index
				+ "'><input type='text' class='form-control' style='text-align:center;' id='inputScore"
				+ index
				+ "' value="
				+ thisScore
				+ "></input><span class='input-group-btn'><button class='btn btn-default' id='updataButton"
				+ index
				+ "' type='button'>修改</button><button class='btn btn-default' id='calcelUpdata"
				+ index + "' type='button'>取消</button></span></div>";
		$("#scoreBox" + index).parent().html(str);
		$("#inputScore" + index).focus();
		var newScore = $("#inputScore" + index).val();
		$("#calcelUpdata" + index).click(
				function() {
					var str2 = '<a class="btn btn-block btn-xs" id="scoreBox'
							+ index + '" href="javascript:inputScore(' + index
							+ ',' + newScore + ',' + thisScid + ',\''
							+ thisProcess + '\');">' + newScore + '</a>'
					$("#inputScoreBox" + index).parent().html(str2);
				});
		$("#updataButton" + index).click(
			function() {
				newScore = $("#inputScore" + index).val();
				if (newScore == thisScore) {
					layer.msg('相同成绩无需修改', {
						offset : [ '180px', '800px' ],
						anim : 6
					});
				} else if (newScore == "" || newScore == null
					|| isNaN(newScore) || newScore < 0
					|| newScore > 100) {
					layer.msg("请输入正确的成绩", {
						offset : [ '180px', '800px' ],
						anim : 6
					});
				} else {
					layer.confirm('是否修改成绩？',{
						offset : '200px',
						btn : [ '确定', '取消' ]
						// 按钮
					},function() {
						layer.load();
						$.ajax({
							url : "http://localhost:8080/shf5790_1/upDataScore?scId="+ thisScid+ "&score="+ newScore,
							type : "GET",// 方法类型
							async : true,
							success : function(res) {
								console.log(res)
								setTimeout(
									function() {
										layer.closeAll('loading');
								},800);
								setTimeout(function() {
									layer.msg('修改成功',{
										offset : '240px',
										icon : 1
									});
									var str3 = '<a class="btn btn-block btn-xs" id="scoreBox'+ index
										+ '" href="javascript:inputScore('+ index+ ','+ res[0].score+ ','
										+ thisScid+ ',\''+ thisProcess+ '\');">'+ res[0].score+ '</a>'
										$("#inputScoreBox"+ index).parent().html(str3);
										$("tbody tr td:eq(4)").html(res[0].gpa)
								},1200);
							},
							error : function(data) {
								layer.msg("操作异常："	+ data+ "   "+ data.responseText,{
									icon : 5
								});
							}
						});
				}, function() {});
			}
		})
	} else {
		layer.msg("该学生正在" + thisProcess, {
			offset : [ '180px', '800px' ],
			anim : 6
		});
	}
}
function getCId() {
	var url = window.location;
	function getUrlParam(url, name) {
		var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
		var matcher = pattern.exec(url);
		var items = null;
		if (matcher != null) {
			try {
				items = decodeURIComponent(decodeURIComponent(matcher[1]));
			} catch (e) {
				try {
					items = decodeURIComponent(matcher[1]);
				} catch (e) {
					items = matcher[1];
				}
			}
		}
		return items;
	}
	id = getUrlParam(url, 'cid'); // 12
	this.getCName();
	this.getTable();
};
function getCName() {
	$.ajax({
		url : "http://localhost:8080/shf5790_1/CNameDetail?cid=" + id,
		type : "GET",// 方法类型
		async : true,
		// contentType : "application/x-www-form-urlencoded",
		success : function(res) {
			var cName = res;
			$("#course_name").html(cName);
		},
		error : function(data) {
			layer.msg("操作异常：" + data + "   " + data.responseText, {
				icon : 5
			});
		}
	});
};
function getTable() {
	$(function() {
		$('#tb_scores')
				.bootstrapTable(
						{
							url : "http://localhost:8080/shf5790_1/courseStudentsList?cid="
									+ id,
							dataType : "json",/* 数据类型 */
							pagination : true,/* 是否分页 */
							// sortOrder: "asc",
							// search : true, // 是否显示表格搜索
							columns : [
									{
										title : '序号',/**/
										field : 'eiId',/* Json数据对应的字段 */
										align : 'center',
										formatter : function(value, row, index) {
											return index + 1;
										}

									},
									{
										title : '选课学号',
										field : 'SId',
										align : 'center'
									},
									{
										title : '学生姓名',
										field : 'SName',
										align : 'center'
									},
									{
										title : '成绩',
										field : 'score',
										align : 'center',
										formatter : function(value, row, index) {
											return '<a class="btn btn-block btn-xs" id="scoreBox'
													+ row.eiId
													+ '" href="javascript:inputScore('
													+ row.eiId
													+ ','
													+ row.score
													+ ','
													+ row.scId
													+ ',\''
													+ row.eiCprogress
													+ '\')">'
													+ row.score + '</a>';
										}
									}, {
										title : '绩点',
										field : 'gpa',
										align : 'center'
									}, {
										title : '课程进度',
										field : 'eiCprogress',
										align : 'center'
									}, ],
						});
		changeTable();
	});
}
//function changeTable() {
//	setTimeout(function() {
//		var tb_scores = document.getElementById("tb_scores"); // 找到这个表格
//		var rows = tb_scores.rows; // 取得这个table下的所有行
//		for (var i = 0; i < rows.length; i++) {
//			var this_score = rows[i].cells[4].innerHTML;
//			if (this_score < 60) {
//				$("#tb_scores").find("tr").eq(i).addClass("error");
//			}
//		}
//	}, 1000)
//}
function searchByCondition() {
	console.log($('#formSearch').serialize())
	$.ajax({
		url : "http://localhost:8080/shf5790_1/searchByCondition?cid=" + id,
		type : "POST",// 方法类型
		async : true,
		// contentType : "application/x-www-form-urlencoded",
		data : $('#formSearch').serialize(),
		success : function(res) {
			console.log("上传成功")
			console.log(res)
			$('#tb_scores').bootstrapTable('load', res);
		},
		error : function(data) {
			alert("操作异常：" + data + "   " + data.responseText);
		}
	});
}
function getTongji() {
	$.ajax({
		url : "http://localhost:8080/shf5790_1/statisticsScore?cid=" + id,
		type : "GET",// 方法类型
		async : true,
		// contentType : "application/x-www-form-urlencoded",
		success : function(res) {
			console.log(res)
			var str1 = "<span>最高分：" + res[0][0] + "</span><span>最低分："
					+ res[0][1] + "</span><span>平均分：" + res[0][2] + "</span>";
			$("#score_statistics1").html(str1);
			var str2 = "<span>挂科人数：" + res[7] + "人</span><span>及格率：" + res[8]
					+ "%</span>";
			$("#score_statistics2").html(str2);
			$('#bar-7').jqbar({
				label : '60分以下',
				value : Math.round(res[1]),
				barColor : '#D64747',
				orientation : 'v'
			});

			$('#bar-8').jqbar({
				label : '60-69分',
				barColor : '#FF681F',
				value : Math.round(res[2]),
				orientation : 'v'
			});

			$('#bar-9').jqbar({
				label : '70-79分',
				barColor : '#ea805c',
				value : Math.round(res[3]),
				orientation : 'v'
			});

			$('#bar-10').jqbar({
				label : '80-89分',
				barColor : '#88bbc8',
				value : Math.round(res[4]),
				orientation : 'v'
			});

			$('#bar-11').jqbar({
				label : '90-99分',
				barColor : '#939393',
				value : Math.round(res[5]),
				orientation : 'v'
			});

			$('#bar-12').jqbar({
				label : '满分',
				barColor : '#3a89c9',
				value : Math.round(res[6]),
				orientation : 'v'
			});
		},
		error : function(data) {
			layer.msg("操作异常：" + data + "   " + data.responseText, {
				icon : 5
			});
		}
	});
}