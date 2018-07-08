var DiaryItems = [];
var count = 0;
User = require('./user.js');
function getSummary(){
	var num1 = Math.floor(Math.random()*50+1);
	var num2 = Math.floor(Math.random()*20000+1);
	var str = "天数： "+num1+" 浏览次数： "+num2;
	return str;
}
function Diary(name,title,post,image,type,location,price, liquidateDamages=100, startAt="20171101", endAt="20171103"){
	this.name = name;
	this.title = title;
	this.post = post;
	this.image = image;
	this.type = type;
	this.location = location;
	this.summary = getSummary();
	this.price = price;
	this.liquidateDamages = liquidateDamages;
	this.startAt = startAt;
	this.endAt = endAt;
	this.flag = true;
};
module.exports = Diary;

Diary.prototype.save = function(){
		var diary = {
		name: this.name,
		title: this.title,
		post: this.post,
		image: this.image,
		type: this.type,
		location: this.location,
		summary: this.summary,
		price: this.price,
		liquidateDamages: this.liquidateDamages,
		startAt: this.startAt,
		endAt: this.endAt,
		flag: true,
	};
	DiaryItems[count] = diary;
	count++;
	console.log("Diary Save ",diary);
};
Diary.delete = function(name,title){
	var i = 0;
	while(i < count){
		if(DiaryItems[i].name == name){
			if(DiaryItems[i].title == title){
				DiaryItems[i].flag = false;
				break;
			}
		}
		i++;
	}
	return DiaryItems;
};
Diary.get = function(name){
	var i = 0;
	var result = [];
	var tot = 0;
	if(count == 0){
		return null;
	}
	while(i < count){
		if((DiaryItems[i].name == name) && (DiaryItems[i].flag)){
			result[tot++] = DiaryItems[i];
		}
		i++;
	}
	return result;
}
Diary.prototype.getAll = function(){
	var result = [];
	var i = 0;
	while(i < count){
			var it = DiaryItems[i];
			var tempuser = new User({
						name:'2',
						password:'2',
						avatar:'images/1.jpg',
				});
			tempuser.save();
			tempuser = tempuser.get(it.name);
			var temp = {"name":it.name ,"title":it.title,"summary":it.summary,"image":it.image,"type":it.type,"location":it.location,"avatar":tempuser.avatar,"post":it.post,"price":it.price};
			//var temp2 = JSON.stringify(temp);
			result[i] = temp;
		i++;
	}
	return result;
}
Diary.prototype.getProducer = function(){
	var result = [];
	var i = 0;
	var tot = 0;
	while(i < count){
		if(DiaryItems[i].type == "producer"){
			var it = DiaryItems[i];
			var tempuser = new User({
						name:'2',
						password:'2',
						avatar:'images/1.jpg',
				});
			tempuser.save();
			tempuser = tempuser.get(it.name);
			var temp = {"name":it.name ,"title":it.title,"summary":it.summary,"image":it.image,"type":it.type,"location":it.location,"avatar":tempuser.avatar,"post":it.post,"price":it.price};
			//var temp2 = JSON.stringify(temp);
			result[tot++] = it;
		}
		i++;
	}
	return result;
}
Diary.prototype.getConsumer = function(){
	var result = [];
	var i = 0;
	var tot = 0;
	while(i < count){
		if(DiaryItems[i].type == "consumer"){
			var it = DiaryItems[i];
			var tempuser = new User({
						name:'2',
						password:'2',
						avatar:'images/1.jpg',
				});
			tempuser.save();
			tempuser = tempuser.get(it.name);
			var temp = {"name":it.name ,"title":it.title,"summary":it.summary,"image":it.image,"type":it.type,"location":it.location,"avatar":tempuser.avatar,"post":it.post,"price":it.price};
			result[tot++] = it;
		}
		i++;
	}
	return result;
}