# language
多语言切换工具

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
   repositories {
       maven { url 'https://jitpack.io' }
   }
}
```
Step 2. Add the dependency
```gradle
dependencies {
    implementation 'com.github.bingoloves:language:1.0.0'
}
```
**使用示例**
```java
// App初始化
class BaseApplication extends android.app.Application{
     @Override
        protected void attachBaseContext(Context base) {
            // 国际化适配（绑定语种）
            super.attachBaseContext(MultiLanguages.attach(base));
        }
}
//Activity
class MainActivity extends android.app.Activity{
      @Override
        protected void attachBaseContext(Context newBase) {
            // 国际化适配（绑定语种）
            super.attachBaseContext(MultiLanguages.attach(newBase));
        }
        
      /**
       * 切换语言(中英文切换)
       */
      private void switchLanguage() {
         Locale locale = MultiLanguages.getAppLanguage(MainActivity.this);
         boolean isEnglish;
         if (MultiLanguages.equalsCountry(locale, Locale.CHINA)) {
             isEnglish = false;
         } else if (MultiLanguages.equalsLanguage(locale, Locale.ENGLISH)) {
             isEnglish = true;
         } else {
             isEnglish = false;
         }
         boolean restart;
         if (isEnglish){
             restart = MultiLanguages.setAppLanguage(this, Locale.CHINA);
         } else {
             restart = MultiLanguages.setAppLanguage(this, Locale.ENGLISH);
         }
         // 是否需要重启
         if (restart){
            // 1.使用recreate来重启Activity的效果差，有闪屏的缺陷
            // recreate();
            // 2.我们可以充分运用 Activity 跳转动画，在跳转的时候设置一个渐变的效果，相比前面的这种带来的体验是最佳的
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
         }
      }
}
  

```
