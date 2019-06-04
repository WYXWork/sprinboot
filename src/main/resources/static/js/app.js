layui.extend({
}).define(['element', 'form', 'table','layer'], function(exports) {
    var $ = layui.jquery,
        element = layui.element,
        layer = layui.layer,
        form = layui.form,
        table = layui.table,
        _win = $(window),
        _doc = $(document),
        _body = $("body");
    var app = {
        Loading: function(bool, text) {
        	var ajaxbg = $("#loading_background,#loading");
        	if(!bool){
                ajaxbg.hide();
        	} else if(!(ajaxbg.is(':visible') && bool)){
        	    if (!!text) {
        	        $("#loading").css("left", ($('body').width() - $("#loading").width()) / 2);
        	        $("#loading span").html(text);
        	    } else {
        	        $("#loading").css("left", ($('body').width() - $("#loading").width()) / 2);
        	        $("#loading span").html("正在为您加载…");
        	    }
        	    ajaxbg.show();
            }
        },
        dialogClose: function(){
        	var index = parent.layer.getFrameIndex(window.name);
        	parent.layer.close(index);
        },
        dialogAlert: function(content, options) {
        	var defaults = {
                title: '提示',
            };
            var options = $.extend(defaults, options);
            if (options.type == -1) {
            	options.type = 2;
            }
            layer.alert(content, {
                icon: options.type,
                title: options.title
            });
        },
        dialogContent: function(content){
        	layer.open({
        		type: 1,
        		closeBtn: 0,
        		anim: 2,
        		shadeClose: true,
        		content: content
        	});
        },
        dialogConfirmOption: function(options) {
        	var defaults = {
    			type:1,
        		icon: 7,
                title: "提示",
                btn: ['确认', '取消'],
        		enterEsc: false
        	};
            var options = $.extend(defaults, options);
            var index = layer.confirm(options.content, {
                icon: options.icon,
                title: options.title,
                btn: options.btn,
                success:function(){
                	if(options.enterEsc){
	            		this.enterEsc = function (event) {
	            			if (event.keyCode === 13) {
	            				$(".layui-layer-btn0").click();
	            				return false;
	            			}else if(event.keyCode == 27){
	            				$(".layui-layer-btn1").click();
	            				return false; 
	            			}
	            		};
	            		$(document).on('keydown', this.enterEsc);
                	}
            	},
            	end:function(){
                	if(options.enterEsc){
                		$(document).off('keydown',this.enterEsc);
                	}
            	}
            }, function () {
            	options.callBack(true);
            }, function () {
            	options.callBack(false);
            });
            return index;
        },
        dialogConfirm: function(content, callBack) {
            return this.dialogConfirmOption({content:content,callBack:callBack});
        },
        dialogPrompt: function(title,formType,callBack) {
        	var index = layer.prompt({title: title, formType: formType}, function(content, index){
        		 if(callBack(content,index)){
        			 layer.close(index);
        		 }
        	});
        },
        /**
         * 1:绿勾 2:红叉 3：黄? 4:灰锁  5:红哭脸  6：绿笑脸  7:橙！ 
         */
        dialogMsg: function(content, type, end) {
            if (type == -1) {
                type = 2;
            }
            top.layer.msg(content, { icon: type, time: 2000, shift: 5 },end);
        },
        dialogOpen: function(options) {
            var defaults = {
                id: null,
                title: '系统窗口',
                width: "800px",
                height: "400px",
                btnAlign : 'rt',
                url: '',
                maxmin: false,
                shade: 0.3,
                btn: ['保存'],
                callBack: null,
                full: false,
                enterKey: false,
                escKey: false
            }, that = this;
            var options = $.extend(defaults, options);
            var _url = options.url;
            var _width = that.windowWidth() > parseInt(options.width.replace('px', '')) ? options.width : that.windowWidth() + 'px';
            var _height = that.windowHeight() > parseInt(options.height.replace('px', '')) ? options.height : that.windowHeight() + 'px';
            var index = layer.open({
                id: options.id,
                type: 2,
                shade: options.shade,
                title: options.title,
                maxmin: options.maxmin,
                fix: false,
                area: [_width, _height],
                btnAlign : options.btnAlign,
                successClose:options.successClose,
                content: _url,
                btn: options.btn,
                yes: function (index, layero) {
                    options.callBack(index, layero);
                },
                cancel: function () {
                    if (options.cancel != undefined)
                    {
                        options.cancel();
                    }
                    return true;
                },
                success: function(layero, index){
                	if(options.enterKey){
                		this.enterConfirm = function(event){
                            if(event.keyCode === 13){
                                $(".layui-layer-btn0").click();
                                return false;
                            }
                        };
                        $(document).on('keydown', this.enterConfirm);
                	}
                	if(options.escKey){
                		this.escQuit = function(event){
                            if(event.keyCode === 0x1B){
                                layer.close(index);
                                return false;
                            }
                        };
                        $(document).on('keydown', this.escQuit);
                	}
                },
                end: function(){
                	if(options.escKey){
                		$(document).off('keydown', this.escQuit);
                	}
                	if(options.enterKey){
                        $(document).off('keydown', this.enterConfirm);
                	}
                },
                btn2:function (layero, index) {
                    if (options.btn2 != undefined){
                        return options.btn2(layero, index);
                    }else{
                    	return true;
                    }
                },
                btn3:function (layero, index) {
                    if (options.btn3 != undefined){
                        return options.btn3(layero, index);
                    }else{
                    	return true;
                    }
                }
            });
            if(options.full){
        		layer.full(index);
        	}
            return index;
        },
        confirmAjax: function(options) {
            var defaults = {
                msg: "提示信息",
                loading: "正在处理数据...",
                url: "",
                param: [],
                type: "post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                //contentType:"application/json",//特殊要求可启用   
                success: null
            }, that = this;
            var options = $.extend(defaults, options);
            var dialogIdx = that.dialogConfirm(options.msg, function (r) {
                if (r) {
                	layer.close(dialogIdx);
                	that.Loading(true, options.loading);
                    window.setTimeout(function () {
                        var postdata = options.param;
                        $.ajax({
                            url: options.url,
                            data: postdata,
                            type: options.type,
                            contentType: options.contentType,   
                            dataType: options.dataType,
                            success: function (data) {
                            	that.Loading(false);
                        		if(data.statusCode==-1){
                        			that.dialogAlert(data.message, {type:-1});
                        		} else {
                        			that.dialogMsg(data.message, 1);
                        			if(options.success!=null){
                        				options.success(data);
                        			}
                        		}
                            }
                        });
                    }, 200);
                }
            });
        },
        topWidth: function() {
            return $(top).width();
        },
        topHeight: function() {
            return $(top).height();
        },
        windowWidth: function() {
            return $(window).width();
        },
        windowHeight: function() {
            return $(window).height();
        },
        getBrowserName: function () {
            var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
            var isOpera = userAgent.indexOf("Opera") > -1;
            if (isOpera) {
                return "Opera"
            }; //判断是否Opera浏览器
            if (userAgent.indexOf("Firefox") > -1) {
                return "FF";
            } //判断是否Firefox浏览器
            if (userAgent.indexOf("Chrome") > -1) {
                if (window.navigator.webkitPersistentStorage.toString().indexOf('DeprecatedStorageQuota') > -1) {
                    return "Chrome";
                } else {
                    return "360";
                }
            }//判断是否Chrome浏览器//360浏览器
            if (userAgent.indexOf("Safari") > -1) {
                return "Safari";
            } //判断是否Safari浏览器
            if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
                return "IE";
            }; //判断是否IE浏览器
        },
        curAdminIframeId: function(){
        	var iframeId = top.$("div.wf-tab-body-admin:visible").attr("lay-item-id");
        	return iframeId;
        },
        curAdminIframe: function(){
        	var browserName = app.getBrowserName();
        	if (browserName == "Chrome" || browserName == "FF") {
                return top.frames['adminiframe' + curAdminIframeId()].contentWindow;
            }
            else {
                return top.frames['adminiframe' + curAdminIframeId()];
            }
        },
        openNewPage: function(option){
        	var that = this,
            _config = that.config;
        	if (_config.type === 'single') {
            	$("#adminiframe0").attr("src",_config.ctx + option.url);
            } else if (_config.type === 'tab') {
                tab.tabAdd(option);
            }
        },
        loadData: function(options) {
        	var defaults = {
        			url: "",
        			param: [],
        			type: "post",
        			dataType: "json",
        			success: null
        	};
        	var options = $.extend(defaults, options);
        	$.ajax({
                url: options.url,
                data: options.param,
                type: options.type,
                dataType: options.dataType,
                success: function (data) {
            		options.success(data);
                }
            });
        	
        },
        initSelect: function(options) {
        	var defaults = {
        		id: "",
                url: "",
                param: [],
                type: "post",
                dataType: "json",
                success: null,
                change:null,
            };
            var options = $.extend(defaults, options);
        	$.ajax({
                url: options.url,
                data: options.param,
                type: options.type,
                dataType: options.dataType,
                success: function (data) {
                	var selectEl = $("#"+options.id);
                	selectEl.empty();
                	selectEl.append("<option value=''></option>"); 
                	$.each(data,function(index,item){
                		selectEl.append("<option value='"+item.value+"' "+item.selected+" "+item.diabled+">"+item.label+"</option>"); 
                	});
                	if(!data.success){
                		options.success(data);
                	}
                	if(!data.change){
                		selectEl.bind("change",data.change);
                	}
                }
            });
        },
        excelExp: function(options){
        	var defaults = {
        	        url: "",
        	        param: {},
        	        type: "post"
            };
            var options = $.extend(defaults, options);
            var form = $("<form></form>").attr("action", options.url).attr("method", options.type);
            for (var key in options.param){
            	form.append($("<input></input>").attr("type", "hidden").attr("name", key).attr("value", options.param[key]));
            }
            form.appendTo('body').submit().remove();
        },
        getTableSelectIds: function(data){
        	var ids = [];
        	for(j = 0,len=data.length; j < len; j++) {
        		ids.push(data[j].id);
        	}
        	return ids.join(",");
        },
        postForm: function (options) {
            var defaults = {
                url: "",
                param: [],
                type: "post",
                dataType: "json",
                loading: "正在处理数据...",
                contentType: "application/x-www-form-urlencoded",
                success: null,
                close: true,
                successClose: true
            },that = this;
            var options = $.extend(defaults, options);
            that.Loading(true, options.loading);
            window.setTimeout(function () {
                $.ajax({
                    url: options.url,
                    data: options.param,
                    type: options.type,
                    dataType: options.dataType,
                    contentType: options.contentType,
                    success: function (data) {
                    	that.Loading(false);
                        if (data.statusCode == -1) {
                        	that.dialogAlert(data.message, {type:-1});
                        } else {
                        	if(options.success!=null){
                        		if(!!data.message){
                        			that.dialogMsg(data.message, 1);
                        		}
                        		options.success(data);
                        		if (options.successClose) {
                        			that.dialogClose();
                        		}
                        	} else {
                        		if(!!data.message){
                        			that.dialogMsg(data.message, 1,function(){
                        				if (options.successClose) {
                        					that.dialogClose();
                                        }
                        			});
                        		}
                        	}
                        }
                    }
                });
            }, 500);
        }
    },
    appEvents = app.events = {
    	userCenter: function(e){
    		app.openNewPage({url: "/sys/User/center", icon: "fa-user-circle-o", title: "用户中心", id: "UserCenter"});
    	},
		fullScreen: function(e) {
			var a = "layui-icon-screen-full",
				i = "layui-icon-screen-restore",
				t = e.children("i");
			if (t.hasClass(a)) {
				var l = document.body;
				l.webkitRequestFullScreen ? l.webkitRequestFullScreen() : l.mozRequestFullScreen ? l.mozRequestFullScreen() : l.requestFullScreen && l.requestFullscreen(), t.addClass(i).removeClass(a)
			} else {
				var l = document;
				l.webkitCancelFullScreen ? l.webkitCancelFullScreen() : l.mozCancelFullScreen ? l.mozCancelFullScreen() : l.cancelFullScreen ? l.cancelFullScreen() : l.exitFullscreen && l.exitFullscreen(), t.addClass(a).removeClass(i)
			}
		 },
		 signOut: function(e){
			 app.dialogConfirmOption({content:'确认注销登录信息，退出系统吗？',callBack:function(r){
         		if(r){
         			window.setTimeout(function () {
         				$.ajax({
         					url: app.config.ctx + "/sys/User/Logout.json",
         					data: {},
         					type: "post",
         					dataType: "text",
         					success: function (data) {
         						app.Loading(false);
         						if(data=="1"){
         							window.location.href= app.config.ctx + "/admin/login";
         						}
         					}
         				});
         			}, 200);
         		}
         	},title:'注销提示',btn:['确认注销','取消']});
		 }
    };
    _body.on("click", "*[layadmin-event]", function() {
		var e = $(this),
			i = e.attr("layadmin-event");
		appEvents[i] && appEvents[i].call(this, e)
	});
    $.ajaxSetup({
    	error: function (XHR, TS) {
    		app.Loading(false);
    		if(XHR.responseText=='timeout'){
    			app.dialogMsg('登录已经超时，请重新登录！', -7);
    			top.location.reload();
    		} else {
    			app.dialogMsg('请求错误,错误代码：'+XHR.status, -1);
    		}
        },
        beforeSend: function () {
        	//$.Loading(true);
        },
        complete: function () {
        	app.Loading(false);
        }
    });
    exports('app', app);
});