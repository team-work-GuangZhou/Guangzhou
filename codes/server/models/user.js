var UserItems = {};
var count = 0;
Diary = require('../models/diary.js');

function User(user){
	this.name = user.name;
	this.password = user.password;
	this.avatar = user.avatar;
};
module.exports = User;

User.prototype.save = function(){
	var user = {
		name: this.name,
		password: this.password,
		avatar: this.avatar,
	};
	UserItems[count] = user;
	count++;
};

User.prototype.get = function(name){
	var i = 0;
	while(i < count){
		if(UserItems[i].name == name){
			var user = UserItems[i];
			return user;
		}
		i++;
	}
};
User.prototype.adddiary = function(name,title,post,image,type,location,price,liquidateDamages=100, startAt="20171101", endAt="20171103"){
	var newdiary = new Diary(name,title,post,image,type,location,price,liquidateDamages, startAt, endAt);
	newdiary.save();
};
User.prototype.getdiary = function(name){
	var result = Diary.get(name);
	return result;
};

User.prototype.deletediary = function(name,title){
	return Diary.delete(name,title);
};