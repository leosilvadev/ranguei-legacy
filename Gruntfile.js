module.exports = function(grunt) {

	grunt.initConfig({
		copy : {
			public : {
				expand : true,
				cwd : 'app',
				src : '**',
				dest : 'dist'
			}
		},

		clean : {
			dist : {
				src : 'dist'
			}
		},

		imagemin : {
			public : {
				expand : true,
				cwd : 'dist/img',
				src : '**/*.{png,jpg,gif}',
				dest : 'dist/img'
			}
		},

		useminPrepare : {
			html : 'dist/**/*.html'
		},

		jshint : {
			js : {
				src : [ 'app/js/**/*.js' ]
			}
		},

		rev : {
			options : {
				encoding : 'utf8',
				algorithm : 'md5',
				length : 8
			},
			imgs : {
				src : [ 'dist/img/**/*.{png,jpg,gif}' ]
			},
			mins : {
				src : [ 'dist/js/**/*.min.js', 'dist/css/**/*min.css' ]
			}
		},

		watch : {
			js : {
				options : {
					event : [ 'changed' ]
				},
				files : 'app/js/**/*.js',
				tasks : 'jshint:js'
			}
		},

		browserSync : {
			public : {
				bsFiles : {
					src : [ 'app/**/*' ]
				}
			},
			options : {
				watchTask : true,
				server : {
					baseDir : 'app'
				}
			}
		},

		usemin : {
			html : 'dist/**/*.html'
		}
	});

	grunt.registerTask('dist', [ 'clean', 'copy' ]);
	grunt.registerTask('min', [ 'useminPrepare', 'concat', 'uglify', 'cssmin', 'rev:imgs', 'rev:mins', 'usemin', 'imagemin' ]);
	grunt.registerTask('default', [ 'dist', 'min' ]);
	grunt.registerTask('server', [ 'browserSync', 'watch' ]);

	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-imagemin');
	grunt.loadNpmTasks('grunt-usemin');

	grunt.loadNpmTasks('grunt-rev');
	grunt.loadNpmTasks('grunt-browser-sync');
}
