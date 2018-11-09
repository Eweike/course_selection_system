var id; // 当前课程的id，全局变量
var pathUrl = "E:/work_path/six/sun5790/test.xls";
$(document).ready(function(e) {
	console.log("进入页面")
	getCId();
	$("#btnStatistics").click(function() {
		subScore();
	})
	$("#fileImport").click(function () {
        $("#files").click();
    })
});
function getCId() {
	console.log("开始获取CID")
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
	console.log("Cid==="+id)
	this.getCName();
	this.getTable();
};
function getCName() {
	console.log("开始获取课程名称")
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
							url : "http://localhost:8080/shf5790_1/courseStudentsList?cid="+ id,
							dataType : "json",/* 数据类型 */
							pagination : true,/* 是否分页 */
							search : true, // 是否显示表格搜索
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
											return "<input type='text' class='form-control scoreInput' style='text-align:center;' id='inputScore"
													+ row.eiId
													+ "' placeholder='请输入成绩'"
													+ " name='inputScore"
													+ index + "'></input>";
										}
									} ],
						});
	});
}
function subScore() {
	var state = true;
	$("#tb_scores input[type='text']").each(function(){
	    var newScore = $(this).val();
	    if (newScore == "" || newScore == null || isNaN(newScore) || newScore < 0
			|| newScore > 100) {
		layer.msg("请输入正确的数字", {
			offset : [ '180px', '800px' ],
			anim : 6
		});
		state = false;
	}
	});
	if (state) {
		layer.confirm('是否添加成绩？', {
			offset : '200px',
			btn : [ '确定', '取消' ]
		}, function() {
			layer.load();
			$.ajax({
				url : "http://localhost:8080/shf5790_1/subScore?cid=" + id,
				type : "POST",// 方法类型
				async : true,
				contentType : "application/x-www-form-urlencoded",
				data : $('#scoreForm').serialize(),
				success : function(res) {
					setTimeout(function() {
						layer.closeAll('loading');
					}, 800);
					setTimeout(function() {
						layer.msg('提交成功', {
							offset : '240px',
							icon : 1
						});
					}, 1400);
					setTimeout(function() {
						window.location.href = "myCourse-teacher.jsp";
					}, 2400);
				},
				error : function(data) {
					alert("操作异常：" + data + "   " + data.responseText);
				}
			});
		}, function() {

		});
	}
};
function getvl(obj) {
	var path = obj.value;
    var path1 = path.lastIndexOf("\\");
    var urlName = path.substring(path1+1);
    var url = this.location.href
    var pos = url.lastIndexOf("/")==-1?url.lastIndexOf("\\"):url.lastIndexOf("/");
    var rootPath = url.substring(0,pos+1);
    rootPath = rootPath.replace("file:///","");
    var urlPath = rootPath+urlName;
	$.ajax({
		url : "http://localhost:8080/sun5790/loadScoreInfo?urlPath="+pathUrl,
		type : "Get",// 方法类型
		async : true,
		// contentType : "application/x-www-form-urlencoded",
		success : function(res) {
			console.log(res)
			for (var i = 0; i < res.length; i++) {
				var new_sId = res[i].SId;
				var new_sName = res[i].SName;
				var new_cId = res[i].cid;
				var new_score = res[i].score;
				
				var old_id = $("tbody tr:eq("+i+") td:eq(1)").html();
				var old_sName = $("tbody tr:eq("+i+") td:eq(2)").html();
				if (new_sId==old_id && new_cId==id) {
					$("tbody tr:eq("+i+") td:eq(3) input:first").val(new_score);
				}
			}
		},
		error : function(data) {
			layer.msg("操作异常：" + data + "   " + data.responseText, {
				icon : 5
			});
		}
	});
};

function readFile(fileBrowser) {
    if (navigator.userAgent.indexOf("MSIE")!=-1) //判断是否是ie浏览器
        readFileIE(fileBrowser);
    else if (navigator.userAgent.indexOf("Firefox")!=-1 || navigator.userAgent.indexOf("Mozilla")!=-1) //判断是否是火狐浏览器
        readFileFirefox(fileBrowser);
    else
        alert("Not IE or Firefox (userAgent=" + navigator.userAgent + ")");
}
function readFileFirefox(fileBrowser) {
    try {
        netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
    } 
    catch (e) {
        alert('无法访问本地文件，由于浏览器安全设置。为了克服这一点，请按照下列步骤操作：(1)在地址栏输入"about:config";(2) 右键点击并选择 New->Boolean; (3) 输入"signed.applets.codebase_principal_support" （不含引号）作为一个新的首选项的名称;(4) 点击OK并试着重新加载文件');
        return;
    }
    var fileName=fileBrowser.value; //这一步就能得到客户端完整路径。下面的是否判断的太复杂，还有下面得到ie的也很复杂。
    var file = Components.classes["@mozilla.org/file/local;1"]
        .createInstance(Components.interfaces.nsILocalFile);
    try {
        // Back slashes for windows
        file.initWithPath(fileName.replace(/\//g, '\\\\'));
    }
    catch(e) {
        if (e.result!=Components.results.NS_ERROR_FILE_UNRECOGNIZED_PATH) throw e;
        alert("File '" + fileName + "' cannot be loaded: relative paths are not allowed. Please provide an absolute path to this file.");
        return;
    }
    if ( file.exists() == false ) {
        alert("File '" + fileName + "' not found.");
        return;
    }
    alert(file.path); // I test to get the local file's path.
    var is = Components.classes["@mozilla.org/network/file-input-stream;1"].createInstance( Components.interfaces.nsIFileInputStream );
    try { is.init( file,0x01, 00004, null); }
    catch (e) {
        if (e.result!=Components.results.NS_ERROR_FILE_ACCESS_DENIED) throw e;
        alert("Unable to access local file '" + fileName + "' because of file permissions. Make sure the file and/or parent directories are readable.");
        return;
    }
    var sis = Components.classes["@mozilla.org/scriptableinputstream;1"].createInstance( Components.interfaces.nsIScriptableInputStream );
    sis.init( is );
    var data = sis.read( sis.available() );
    alert("Data from file: " + data); // I test to get the local file's data.
}
function readFileIE(fileBrowser) {
    var data;
    try {
        var fso = new ActiveXObject("Scripting.FileSystemObject");
        var fileName=fso.GetAbsolutePathName(fileBrowser.value);
        if (!fso.FileExists(fileName)) {
            alert("File '" + fileName + "' not found.");
            return;
        }
        var file = fso.OpenTextFile(fileName, 1);
        data = file.ReadAll();
        alert("Data from file: " + data);
        file.Close();
    }
    catch(e) {
        if (e.number == -2146827859) {
            // This is what we get if the browser's security settings forbid
            // the use of the FileSystemObject ActiveX control浅谈FireFox中file控件不能取到客户端文件的完整路径的问题 (转) - 青软学生 - wodexinlihua1 的博客
            alert('Unable to access local files due to browser security settings. To overcome this, go to Tools->Internet Options->Security->Custom Level. Find the setting for "Initialize and script ActiveX controls not marked as safe" and change it to "Enable" or "Prompt"');
        }
        else if (e.number == -2146828218) {
            // This is what we get if the browser can't access the file
            // because of file permissions浅谈FireFox中file控件不能取到客户端文件的完整路径的问题 (转) - 青软学生 - wodexinlihua1 的博客
            alert("Unable to access local file '" + fileName + "' because of file permissions. Make sure the file and/or parent directories are readable.");
        }
        else throw e;
    }
}