# AndroidUtils
### Android工具包

- MainHandler

- BaseActivity内嵌主线程Handler，变量 **mHandler**

  可重写 **BaseActivity** 的 **handleMessage(IHandlerMessage obj, Message msg)** 函数处理消息

- bus包，消息总线

  通过**Bus**类获取总线，实现**Callback**接口，函数**Callback#callback**添加**BusIntercept**注解进行拦截消息&线程处理，发送消息ID为null时，所有Callback接收者都能收到消息



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
