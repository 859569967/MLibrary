# MLibrary
## 自定义基础类库
##### EditTextWithDel
 * 带删除控件的输入框，这个是网上下载过来的文件，使用方法可以到网上去找；
##### MCheckEditView
 * 控件由一个TextView+EditText+CheckBox组成：
 * setLabel(String label) 设置TextView文本；
 * setText(String text) 设置EditText文本；
 * setHint(String hint) 设置EditText提示文本；
 * setCheck(boolean check) 设置CheckBox选择状态；
 * setCheckBg(Drawable bg) 设置CheckBox自定义背景；
 * isCheck() 获取CheckBox选中状态；
 * getText() 获取EditText文本；
 * setEms(int ems) 设置TextView最小显示文本量；
##### MCheckTextView
 * 控件由一个TextView+CheckBox组成：
 * setChecked(boolean isCheck) 设置CheckBox状态；
 * isCheck() 获取CheckBox状态；
 * setEnable(boolean enable) 设置CheckBox是否可用；
 * setLabelText(String text) 设置TextView文本；
##### MImgTextView
 * 控件由TextView+TextView+ImageView组成：
 * setLabelText(String text) 设置文本标签；
 * showStarText(boolean visible) 显示控件状态；
 * setStartText(String startText) 设置控制文本；
 * showImage(boolean visible) 设置图片显示；
 * IImgTextOperationListener 控件点击事件回调；
##### MLabelEditText
 * 控件由TextView+EditText+TextView组成：设计初衷是为了做获取验证码倒计时
 * setCountColor(int color) 设置右边控件的背景颜色；
 * setTvCounterEnable(boolean isEnable) 设置右边控件enable状态；
 * setLabel(String label) 设置标签文本；
 * setEditHint(String hint) 设置EditText提示文本；
 * setEditText(String edit) 设置EditText文本；
 * setCountText(String counter) 设置右边TextView文本；
 * setInputType(int type) 设置输入文本类型；
 * setTime(long time) 设置倒计时时间；
 * setInterval(long interval) 设置时间间隔；
 * setCountVisibility(View view, int v) 设置控件的显示隐藏；
 * getText() 获取EditText文本；
 * setMinEms(int minEms) 设置标签文本的最小长度；
 * IRightClickListener 右边TextView点击事件接口回调；
##### MLabelSpinnerView
 * 控件由TextView+Spinner+TextView组成：
 * setLeftLabel(String label) 设置左边控件的标签文本；
 * setRightLabel(String label) 设置右边控件的标签文本；
 * setAdapter(String[] items) 设置Spinner适配器；
 * getSelectText() 获取当前选择的文本；
 * setSelection(int selection) 设置Spinner默认值；
 * setSelectByValue(String value) 设置Spinner默认值；
 * getSpinner() 获取Spinner控件；
