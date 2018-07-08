window.onload = function(){
		var image = document.getElementById("image");
		var imageData;
		function readFile(file) {                                                       
		    var reader = new FileReader();
		    reader.onload = readSuccess;                                            
		    function readSuccess(evt) { 
		        imageData = evt.target.result;                                
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
		document.getElementById('avatar_select').onchange = function(e) {
		    readFile(e.srcElement.files[0]);
		};
		$(".submit").click(function(){
			var ainput = document.getElementsByTagName('input');
			var name = ainput[0];
			var password = ainput[1];
			var checkpassword = ainput[2];
			var data = {"name":name.value,"password":password.value,"password_repeat":checkpassword.value,"avatar":toBase64(imageData)};
			console.log("REGIST DATA: ",data);
			if(name.value != ""){
				$.ajax({
					url:'/reg',
					type:'post',
					data:data,
					success: function(data, status){
					if(status == 'success'){
							console.log("注册成功跳转")
							location.href = 'detail';
					}
				}
			});
			}
	});
}