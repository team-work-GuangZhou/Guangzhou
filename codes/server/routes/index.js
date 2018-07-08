var express = require('express');
var session = require('express-session');
var router = express.Router();
User = require('../models/user.js');
Diary = require('../models/diary.js');
var session = require('express-session');
var fs = require("fs");
var testuser = new User({
	name:'1',
	password:'1',
	avatar:'images/t1.jpg',
});
testuser.save();
var kls = new User({
	name:'孔令爽',
	password:'konglingshuang',
	avatar:'images/t0.jpg',
});
kls.save();

testuser.adddiary('1','带你游览中大','各位家长们，各位小朋友们，近期是否有来中大游览的意愿呢？我可以带你们游览中大哦~不为人知的中大故事，还有灵异事件传闻哦（坏笑）。\r\n我会带你游览校园内的小礼堂、孙中山纪念铜像、黑石屋、惺亭、乙丑进士牌坊、陈寅恪故居、十八先贤铜像、永芳堂、马丁堂、中大北门广场等有纪念意义的景点。一边游览，一边解读发生在中大的历史故事和历史名人的事迹。对于了解重大，熏陶情感都是非常有好处的。\r\n中午在紫荆园用餐，品味一下中大味道。','images/t2.jpg','producer','大学城','200',60,"2018.5.01","2017.5.01");
testuser.adddiary('1','教你做湖北独特的米酒','我是一名全职妈妈，平时看看孩子，打扫家务。但是我也想闲下来的时候赚点钱，补贴家用，我是地地道道的湖北人，我会做湖北最地道最正宗的米酒。自己做的米酒要比网上买的好喝多了呢！如果你想学习做米酒，可以来租我呀，一天包你会做湖北正宗的米酒，制作米酒的材料我会带你一起去采购，保证健康正宗~特别是外地来旅游的朋友们，会做米酒了，回去做给家人喝呀，健康美味不上头！','images/mijiu.jpg','producer','惠州余明静的家里','400',85,"2018.5.4","2018.5.4");
testuser.adddiary('1', '皑皑白雪，我来带你滑行长白山', '长白山最早记载于《山海经》，称“不咸山（神仙山）”，唐时叫“太白山”，金始称“长白山”，是满清王朝的发源地。长白山特别适合在冬天滑雪，壮丽惊人的自然风光，陡峭与平稳共存的地势，恰到好处的温度，是你不能错过的绝佳选择。在这三天中，我们将会拜访长白山的每一处，中午晚上我们将在山腰的饭馆中享受不一样的山味。', 'images/skating.jpeg', 'producer', '长白山', '400',70,"2018.10.30","2018.11.1");
testuser.adddiary('1', '谁能教我做日照绿茶', '我五一假期要去山东日照玩，听闻当地的绿茶和海景是最出名的两大特色，我害怕在当地买到的茶叶是陈年老茶，而且不正宗（最关键是可能旅游团那种商店坑！！）所以有没有山东日照本地的朋友能亲手教我采摘茶叶和炒茶叶呀~可以亲口喝到自己炒的茶叶才真是满满的幸福感呢~', 'images/greentea.jpg', 'consumer', '山东日照青茶厂', '300',80,"2018.6.30","2018.7.1");
testuser.adddiary('1', '急需一枚斯坦福的小哥哥/小姐姐', '今年暑假要去斯坦福游学啦，但是对于斯坦福的校史，学校内的著名景点，我都不是很了解。尤其是斯坦福的教学楼我很想进去看看，希望有位在斯坦佛大学上学的小哥哥/小姐姐能陪我一起逛校园，并且能够讲解一下~（如果能够借用你的校园卡，去斯坦佛的食堂吃顿饭就更好了！！！）', 'images/stanford.jpg', 'consumer', '斯坦福大学', '300',80,"2018.7.1","2018.7.1");

kls.adddiary('孔令爽','拥抱星月国度的碧海蓝天','土耳其的前世是在历史上响当当的奥斯曼大帝国，这个庞大的帝国曾地跨亚非欧三大洲，扼住大陆交通的咽喉，曾长期是世界上最强大，最繁荣的帝国之一。这个大帝国后来被一点点瘦身，今世的土耳其继承了东罗马帝国的文化和伊斯兰文化，东西方文明在这里得以统合。\r\n第一天我们会去圣索菲亚大教堂，地下水宫和蓝色清真寺；然后在第二天，我们将拜访托普卡帕宫(老皇宫)，伊斯坦布尔考古博物馆，耶尼清真寺Yeni Camii以及香料集市（Spice Bazaar）；最后一天，我们将前往柯拉教堂，博斯普鲁斯海峡游轮，苏莱曼清真寺','images/turkey.jpeg','producer','土耳其','500');
kls.adddiary('孔令爽','贵州镇远','镇远印象悠悠舞阳河，在夜郎古驿道上，孕育一座千年古城～镇远悠悠舞阳河，武断地把镇远一分为二；南岸为卫城，北岸为府城，然后浩荡东去，留下久远，留下传说。在第一天，我们将前往贵州的小七孔景区和荔波县，观赏奇特景色，在第二天，我们会去荔波县、茂兰和三都水族自治县；而在最后，我们将开往安顺和黄果树瀑布，领略那里的壮丽风景。','images/zhenyuan.jpeg','producer','贵州','100',80,"2018.7.1","2018.7.3");
kls.adddiary('孔令爽', '探寻海底世界', '从今天起，做一只潜水的猪。潜水、睡觉，畅游四海。数次在海岛旅行的阿锋,将带你在三天内领略海底遨游的乐趣与奇妙，我将与你一起放逐大海。沙巴作为潜水天堂，各个潜水小岛提供非常完善的潜水服务。不会游泳能不能潜水？哪里可以潜水？如何选择潜店？诸如此类问题，阿锋均在攻略中一一解答。在第一天，我会带你了解器材，明白潜水中最重要的原则。在第二天，我们将前往菲卡尔海滩，在游艇的航行中跳入海底。最后一天，我们将回到达纳苏斯镇，一饱海鲜瘾', 'images/diving.jpeg', 'producer', '沙巴', '1000')


var ImageDex = 0;
/* GET home page. */
router.get('/cur_user',function(req,res){
	var result = {"name":req.session.user.name,"avatar":req.session.user.avatar};
	res.send(result);
});
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Diary',user:{}});
});
router.get('/reg',function(req,res,next){
	res.render('reg',{title:'注册',user:{}});
});
router.post('/reg',function(req,res,next){
	var user = req.body;
	var name = req.body.name;
	var	password = req.body.password;
	var password_re = req.body.password_repeat;
	if(password_re != password){
		console.log("error 密码不一致");
		return res.redirect('/reg');
	}
	var avatar = req.body.avatar;
	var name1 = ""+ImageDex+".jpg";
	ImageDex++;
	var image = "images/" + name1;
	saveBase64ToFile("public/images/" + name1, req.body.avatar);
	var newUser = new User({
		name: name,
		password: password,
		avatar: image,
	});
	req.session.user = newUser;
	console.log("REQ Session User: " + req.session.user);
	newUser.save();
	console.log(newUser);
	res.redirect('/detail');
});
router.get('/login',function(req,res){
	res.render('login',{title:'登录'});
});
router.post('/login',function(req,res){
	var login_user = new User({
		name: req.body.name,
		password: req.body.password,
		avatar: req.body.avatar,
	});
	if(!login_user.get(req.body.name)){
		return res.redirect('/login');
	}
	if(login_user.get(req.body.name).password != req.body.password){
		return res.redirect('/login');
	}
	req.session.user = login_user.get(req.body.name);
	res.redirect('/detail');
});
router.get('/detail',function(req,res,next){
	var posts = Diary.get(req.session.user.name);
	console.log('post: ',posts);
	res.render('detail',{
		title:'Detail',
		user: req.session.user.name,
		posts:posts
	});
});
// router.post('/detail',function(req,res){

// });
router.get('/list',function(req,res){
	var temp_Diary = new Diary();
	var list = temp_Diary.getProducer();
	console.log("LIST: ",list);
	res.send(list);
});
router.get('/list1',function(req,res){
	var temp_Diary = new Diary();
	var list = temp_Diary.getConsumer();
	console.log("LIST: ",list);
	res.send(list);
});
router.get('/diary',function(req,res,next){
	if(req.session.user != ""){
		res.render('diary',{title:'diary'});
	}
	res.redirect('/');
});
router.post('/diary',function(req,res){
	res.writeHead(200, {"Content-Type": "application/json"});
	if(!req.session.user){
		res.end(JSON.stringify({succeed: 0, error: "Not login!!!"}));
		return;
	}
	var name = ""+ImageDex+".jpg";
	ImageDex++;
	var image = "images/" + name;
	saveBase64ToFile("public/images/" + name, req.body.image);
	new Diary(req.session.user.name,req.body.title,req.body.post,image,req.body.type,req.body.location,req.body.price, req.body.liquidateDamages, req.body.startAt, req.body.endAt).save();
	console.log("After diary save");
	res.end(JSON.stringify({succeed: 1}));
});

router.get('/delete',function(req,res){
	res.render('delete');
});
router.post('/delete',function(req,res){
	req.session.user.deletediary(req.session.user.name,req.body.name);
	res.redirect('/detail');
});
router.get('/logout',function(req,res){
	req.session.user = null;
	res.redirect('/');
});
function change(img1){
	img1.onchange = function () {
        var oFReader = new FileReader();
        oFReader.readAsDataURL(this.files[0]);
        oFReader.onload = function (oFREvent) {
            alert(oFREvent.target.result);
        };
    }
}

function saveBase64ToFile(path, data){
	var buffer = new Buffer(data,'base64');
	fs.writeFile(path, buffer,  "binary", function(err) { });
}

module.exports = router;
