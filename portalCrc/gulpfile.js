var gulp = require('gulp');
var jshint = require('gulp-jshint');
var clean = require('gulp-clean');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');


gulp.task('clean', function(){
	return gulp.src('src/main/resources/static/dist')
	.pipe(clean());
});

gulp.task('jshint', function(){
	return gulp.src('src/main/resources/static/public/js/*.js')
	.pipe(jshint())
	.pipe(jshint.reporter('default'));
});

gulp.task('uglify', ['clean'], function(){
	return gulp.src([
		'src/main/resources/static/views/agenda/js/*.js', 
		'src/main/resources/static/views/comunicacao/**/js/*.js',
		'src/main/resources/static/views/comunicacao/js/*.js',
		'src/main/resources/static/views/coordenadoria/js/*.js',
		'src/main/resources/static/views/diaria/funcionario/conta/js/*.js',
		'src/main/resources/static/views/diaria/*.js',
		'src/main/resources/static/views/infra/**/*.js',
		'src/main/resources/static/views/recursosHumanos/**/*.js',
		'src/main/resources/static/views/secretaria/**/*.js',
		'src/main/resources/static/views/unidade/**/*.js',
		'src/main/resources/static/views/usuario/*.js',
		'src/main/resources/static/views/usuario/permissao/js/*.js',
		'src/main/resources/static/views/*.js'
		])
	.pipe(uglify())
	.pipe(concat('all.min.js'))
	.pipe(gulp.dest('src/main/resources/static/dist'));
});

gulp.task('default', ['jshint', 'uglify']);