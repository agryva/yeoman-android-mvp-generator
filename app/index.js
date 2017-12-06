'use strict';

const Generator = require('yeoman-generator'); //Root Yeoman
const yosay = require('yosay'); //Membuat tampilan seperti maskot yeoman
const chalk = require('chalk'); //Mengubah warna text yang ada di yosay
const ncp = require('ncp').ncp; //Asynchronous recursive file copying 
const mkdirp = require('mkdirp'); //Function for create folder

module.exports = class extends Generator {
  constructor(args, opts) {
    super(args, opts);

    this.alltarget = {};  //variable untuk menyimpan inputan user
  }
  init() {

    this.log(yosay(
      'Welcome to ' + chalk.cyan('Gits Android') + ' generator! ' + chalk.red('Just Testing :)')
    ));

    // Function for return prompt
    return this.prompt([{
      type : 'input',
      name : 'name',
      message : "What's Your Project name ? ",
      default : "Gits Superfun"
    }, {
      type    : 'input',
      name    : 'package',
      message : "what's Your Package Name ? ",
      default : "id.gits.superfun"
    }, {
      type    : 'input',
      name    : 'targetSdk',
      message : "What Android SDK Will You Be Targeting ?  ",
      default : 26
    } , {
      type    : 'input',
      name    : 'minSdk',
      message : "What's minimun Android SDK ?  ",
      default : 16
    }
  ]).then((answer) => {
      this.alltarget.appName = answer.name;
      this.alltarget.packageName = answer.package;
      this.alltarget.targetSdk = answer.targetSdk;
      this.alltarget.minSdk = answer.minSdk;
    });
  }

  // Function to run yeoman generator
  writing() {
    const packageDir = this.alltarget.packageName.replace(/\./g, '/');
    const appFolder = 'mvp-java';

    mkdirp('app');
    mkdirp('app/src/main/assets');
    mkdirp('app/src/main/java/' + packageDir);
    mkdirp('app/src/androidTest/java/' + packageDir);
    mkdirp('app/src/debug');
    mkdirp('app/src/release');
    mkdirp('app/src/test/java/' + packageDir);

    const appPath = this.sourceRoot() + '/' + appFolder + '/';
    const copyToSameLocation = filePath => this.fs.copy(appPath + filePath, filePath);
    const copyTemplateToSameLocation = (filePath, props) => {
      this.fs.copyTpl(appPath + filePath, filePath, props);
    };

    const copyAllToSameLocation = filePath => {
      ncp.limit = 1600;
      ncp(appPath + filePath, filePath, err => {
        if (err) {
          return console.error(err);
        }
      });
    };

    const copyDotFile = filePath => {
      const index = filePath.lastIndexOf('/') + 1;
      const dest = filePath.substring(0, index) + `.${filePath.substring(filePath.lastIndexOf('/') + 1)}`;

      this.fs.copy(appPath + filePath, dest);
    };

    this.fs.copy(appPath + 'gradle.properties', 'gradle.properties');
    this.fs.copy(appPath + 'gradlew', 'gradlew');
    this.fs.copy(appPath + 'gradlew.bat', 'gradlew.bat');
    this.fs.copy(appPath + 'settings.gradle', 'settings.gradle');

    copyToSameLocation('app/proguard-rules.pro');

    this.fs.copy(appPath + 'gradle', 'gradle');
    this.fs.copy(appPath + 'app/src/main/res', 'app/src/main/res');
    this.fs.copy(appPath + 'app/src/test/', 'app/src/test/');

    const currentPath = 'androidmvp2';

    copyTemplateToSameLocation('build.gradle', this.alltarget);
    copyTemplateToSameLocation('app/build.gradle', this.alltarget);

    copyTemplateToSameLocation('app/src/main/res/values/strings.xml', this.alltarget);
    copyTemplateToSameLocation('app/src/main/AndroidManifest.xml', this.alltarget);
    copyTemplateToSameLocation('app/src/main/res/layout', this.alltarget);
    this.fs.copyTpl(appPath + `app/src/main/java/${currentPath}`, 'app/src/main/java/' + packageDir, this.alltarget);

    this.fs.copyTpl(appPath + `app/src/test/java/${currentPath}`, 'app/src/test/java/' + packageDir, this.alltarget);
  }
}
