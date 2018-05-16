var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var session = require('express-session');

var index = require('./routes/index');
var routes = require('./routes/index');
var users = require('./routes/users');

var app = express();
app.use(cookieParser());
app.use(session({
  secret: 'LJDKJADSlczcsc.a',
  resave: false,
  saveUninitialized: true,
  cookie: { secure: false, maxAge: 1000 * 60 * 60 * 24 * 15}
}));
app.use(function(req, res, next){
  res.locals.user = req.session.user;
  console.log("App req session : ",req.session.user);
  next();
});

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json({limit: '50mb'}));
app.use(bodyParser.urlencoded({ 
  extended: false,
  limit: "100mb",
  parameterLimit: 1000000000
}));
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', routes);
app.use('/login', routes);
app.use('/reg', routes);
app.use('/detail', routes);
app.use('/list', routes);
app.use('/list1', routes);
app.use('/diary', routes);
app.use('/users', users);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
