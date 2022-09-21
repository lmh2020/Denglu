var $ ={
    ajax:function(options){
        var xhr = null,
            url = options.url,
            method = options.method || "get",
            async = typeof (options.async) == "undefined" ? true : options.async,
            data = options.data || null,
            params = "",
            callback = options.success,
            error = options.error;

        if(data){
            for(var i in data){
                params += i + "=" + data[i] + "&";
            }
            params = params.replace(/&$/,"");
        }
        if(method == "get"){
            url += "?"+params;
        }

        //兼容ie浏览器
        if(typeof XMLHttpRequest != "underfind"){
            xhr = new XMLHttpRequest();
        }else{
            xhr = new ActiveXObject("Microsoft.XMLHTTP");
        }

        //相应XMLHttpRequest对象状态变化的函数
        xhr.onreadystatechange = function(){
            if(xhr.readyState === 4){
                if((xhr.status >=200 && xhr.status <300) || xhr.status === 304){
                    callback && callback(JSON.parse(xhr.responseText))
                }else{
                    error && error();
                }
            }
        }


        //创建请求
        xhr.open(method,url,async);
        //http请求头
        xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        //发送请求
        xhr.send(params);
    }

}
