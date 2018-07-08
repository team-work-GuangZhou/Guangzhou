$(function(argument) {
	var imageData;
	function readFile(file) {                                                       
	    var reader = new FileReader();
	    reader.onload = readSuccess;                                            
	    function readSuccess(evt) { 
	        imageData = evt.target.result; 
	        console.log(imageData);                               
	    };
	    reader.readAsArrayBuffer(file);                                              
	} 
	function toBase64( buffer ) {
	    var binary = '';
	    var bytes = new Uint8Array( buffer );
	    var len = bytes.byteLength;
	    for (var i = 0; i < len; i++) {
	        binary += String.fromCharCode( bytes[ i ] );
	    }
	    return window.btoa( binary );
	}

	document.getElementById('img_select').onchange = function(e) {
	    readFile(e.srcElement.files[0]);
	};

	$("#submit").click(function(){
			var ainput = document.getElementsByTagName('input');
			var title = ainput[0];
			var type = ainput[1];
			var location = ainput[3];
			var post = document.getElementById("post");
			var price = ainput[4];
			var data = {"title":title.value,"type":type.value,"post":post.value,"location":location.value, "image":toBase64(imageData),"price":price.value};
			console.log("POST DATA: ",data);
			$.ajax({
				url:'/diary',
				type:'post',
				data:data,
				success: function(data, status){
					if(status == 'success'){
						console.log("发表成功跳转")
						location.href = 'detail';
					}
				},
				error:function(data, err){
					//location.href = 'regist';
					console.log("发表失败");
				}
			});
	});
})