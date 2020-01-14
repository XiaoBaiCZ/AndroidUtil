# AndroidUtils
### Android工具包

- MainHandler

  主线程Handler

- ErrorUtil

  崩溃日志
  
- ProcessUtil

  判断是否App主进程
  
- SystemUiHelper

  系统UI助手，提供状态栏/导航栏操作

~~~java
SystemUiHelper.get(activity)
    .setStatusBarColor(0)
    .transparentStatusBar()
    .systemUiHeight(new SystemUiAttrCallback() {
        public void statusBarHeight(int height) {
        }
        public void navigationBarHeight(int height) {
        }
    });
~~~


# Apache License
~~~
Copyright (c) 2019, xiaobai

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
~~~
