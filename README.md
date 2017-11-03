#为什么kotlin值得研究
###1、推荐kotilin学习资料  
1、kotlin官方github地址：  
[https://github.com/JetBrains/kotlin](https://github.com/JetBrains/kotlin "https://github.com/JetBrains/kotlin")   
2、官方中文翻译站点：  
[https://www.kotlincn.net/docs/reference/](https://www.kotlincn.net/docs/reference/ "https://www.kotlincn.net/docs/reference/")   
[https://www.gitbook.com/book/hltj/kotlin-reference-chinese/details](https://www.gitbook.com/book/hltj/kotlin-reference-chinese/details) 
###2、Kotlin的身世
Kotlin是JetBrain公司搞出来的，运行在JVM上的一门静态类型语言，它是用波罗的海的一个小岛的名字命名的。  
####用Kotlin创建一个数据类
      data class User(var id: Int = 0, var name: String? = null,
                var role: String? = null, var avatar: String? = null, var mobile: String? = null,
                var company: String? = null, var token: String? = null, var city: String? = null,
                var province: String? = null, var code: String? = null, var rate: Double = 0.toDouble(),
                var company_abbr: String? = null, var api_city_id: Int = 0, var business_license_photo: String? = null,
                var identity_card_front: String? = null, var identity_card_back: String? = null, var id_card_no: String? = null,
                var message: Long = 0, var balance: String? = null, var city_id: String? = null, var focus_count: String? = null,
                var sub_account_count: String? = null, var total_cf: String? = null, var total_credit: String? = null,
                var business_license_no: String? = null, var status: String? = null, var car_dealer_user_id: Int = 0, var car_dealer_type: String? = null)
###3、java的进化  
Kotlin它自己的标准库都是基于Java的语言框架做了许多扩展，java的标准库在kotlin中一样的可以用。
####3.1、通用的集合框架  
#####3.1.1创建一个List   
    val list = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7)
#####3.1.2List转换  
把list所有元素*2  
   
    var  var doubleList = list.map { it * 2 }
#####3.1.3List筛选
筛选list中的偶数 
   
    var oddList = list.filter { it % 2 == 0 }

####3.2、与java相互调用
#####3.2.1 kotlin调用java代码
    mTextView.text = "Hello World!"
  
#####3.2.2 Java调用kotlin代码
       User user=new User();
        user.setId(1);
        user.setName("goat");  
####3.2、扩展
#####3.2.1 扩展函数  
Kotlin 同 C# 类似，能够扩展一个类的新功能而无需继承该类或使用像装饰者这样的任何类型的设计模式。 这通过叫做 扩展 的特殊声明完成。Kotlin 支持 扩展函数和扩展属性。 
     
    fun <T> ArrayList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该list
    this[index1] = this[index2]
    this[index2] = tmp
    } 

####3.2.2 扩展属性
    val <T> List<T>.lastIndex: Int
    get() = size - 1
####3.2.3 扩展类
    fun User.bearerToken(): String {
    return "Bearer " + token
    }
####3.3 委托属性
有一些常见的属性类型，虽然我们可以在每次需要的时候手动实现它们， 但是如果能够为大家把他们只实现一次并放入一个库会更好。例如包括：延迟属性（lazy properties）；可观察属性（observable properties）；
把多个属性储存在一个映射（map）中，而不是每个存在单独的字段中。
#####3.3.1 延迟属性
    val user: User by lazy {
        User()
    }
其值只在首次访问时计算；
#####3.3.2 可观察属性
    var name: String by Delegates.observable("<no name>") { prop, old, new ->
        println("$old -> $new")
    }

监听器会收到有关此属性变更的通知；
#####3.3.3 把属性储存在映射中
	//自身委托
	class Person(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
	}
	//构造函数接受一个映射参数
	val person = Person(mapOf(
    "name" to "John Doe",
    "age"  to 25
	))
	//委托属性会从这个映射中取值
	println(person.name) // Prints "John Doe"
	println(person.age)  // Prints 25

###4、kotlin与Android
####4.1 替代findViewById与butterknife
每一位安卓开发人员对 findViewById() 这个方法再熟悉不过了，毫无疑问脏乱的代码令后续开发无从下手的。 尽管存在一系列的开源库如butterknife能够为这个问题带来解决方案，然而对于运行时依赖的库，需要为每一个 View 注解变量字段。现在 Kotlin 安卓扩展插件能够提供与这些开源库功能相同的体验，不需要添加任何额外代码，也不影响任何运行时体验。

    import kotlinx.android.synthetic.main.activity_main.*

	class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text="Hello World!"
        
    }
	}
需要在build.gradle 文件中启用 Gradle kotlin安卓扩展插件：

	apply plugin: 'kotlin-android-extensions'

###4.2 利用拓展类实现工具类的封装

####4.2.1 ToastUtils kotlin上的实现  
	inline fun Context.toast(c: CharSequence) {
    Toast.makeText(this, c, Toast.LENGTH_SHORT).show()
	}
直接调用toast("测试toast")就可以了	


####4.2.2 LogUtils kotlin上的实现  

	inline fun <reified T> T.log(log: Any){
    Log.e(T::class.simpleName, log.toString())
    }
	//调用log("测试log")
这样就会输出以这个类名为TAG的日志。 
####4.2.3 启动activity 
	inline fun <reified T> Activity.startActivity(bundle: Bundle=Bundle()) {
    var intent = Intent(this, T::class.java);
    intent.putExtras(bundle)
    this.startActivity(android.content.Intent())
	}
	//如startActivity<OtherActivity>()
调用startActivity<需要启动的activity类名>()	  
这里startActivity函数里面有个bundle参数，但是我们调用时并没有传bundle参数，这里用到了一个默认参数，可以在函数的方法中直接给参数赋一个默认的值，当没有传值时去默认值，有传值取传递的参数。与Java相比，这可以减少重载数量。

###总结  
这里需要简单介绍Kotlin在泛型中的一个比较重要的增强，这个在Java中无论如何也是做不到的：inline、reified。我们再来回头看一下debug这个方法，我们发现它可以通过泛型参数T来获取到T的具体类型，并且拿到它的类名——当然，如果你愿意，你甚至可以调用它的构造方法来构造一个对象出来——为什么Kotlin可以做到呢？因为这段代码是inline的，最终编译时是要编译到调用它的代码块中，这时候T的类型实际上是确定的，因而Kotlin通过reified这个关键字告诉编译器，T这个参数可不只是个摆设，我要把它当实际类型来用呢。
###4.3 委托属性封装SharedPreference
	class Preference<T>(val context: Context, val name: String, val default: T) : ReadWriteProperty<Any?, T> {

    val prefs by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    private fun <U> findPreference(name: String, default: U): U = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }

        res as U
    }

    private fun <U> putPreference(name: String, value: U) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }
	}
应用示例代码：
	class OtherActivity : AppCompatActivity() {
    private var string:String by Preference<String>(this,"aInt","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
        textView.text = "OtherActivity"
        string=edit.text.toString()
        read.setOnClickListener({
            log(string)
        })
    }
	}










