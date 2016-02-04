# Koi

## A lightweight Kotlin library for Android (only ~160k)

## Gradle

```gradle
    compile 'com.mcxiaoke.koi:core:0.3.+' // useful extensions (only ~90k)
    compile 'com.mcxiaoke.koi:async:0.3.+' // async functions (only ~70k)
```

## Usage

<!-- toc -->
  * [Context Extensions](#context-extensions)
    * [Activity Functions](#activity-functions)
    * [Fragment Functions](#fragment-functions)
    * [Easy to use Toast](#easy-to-use-toast)
    * [Easy to Inflate Layout](#easy-to-inflate-layout)
    * [Useful Functions](#useful-functions)
    * [Easy to create Intent](#easy-to-create-intent)
    * [Easy to Start Activity](#easy-to-start-activity)
    * [Easy to Start Service](#easy-to-start-service)
    * [Network State](#network-state)
    * [Notification Builder](#notification-builder)
    * [Package Functions](#package-functions)
    * [System Service](#system-service)
    * [Easy to Log](#easy-to-log)
  * [View Extensions](#view-extensions)
    * [View Listeners 1](#view-listeners-1)
    * [View Listeners 2](#view-listeners-2)
    * [ListView Listeners](#listview-listeners)
    * [View Utils](#view-utils)
  * [Adapter Extensions](#adapter-extensions)
    * [Easy to Create Adapter](#easy-to-create-adapter)
  * [Bundle Extensions](#bundle-extensions)
    * [Bundle Builder](#bundle-builder)
  * [Parcelable Extensions](#parcelable-extensions)
    * [Easy to create Parcelable](#easy-to-create-parcelable)
  * [Collection Extensions](#collection-extensions)
    * [Collection to String](#collection-to-string)
    * [Map to String](#map-to-string)
    * [List Append](#list-append)
  * [Database Extensions](#database-extensions)
    * [Easy to get Cursor Value](#easy-to-get-cursor-value)
    * [Easy to convert Cursor to Model](#easy-to-convert-cursor-to-model)
    * [Easy to use Transaction](#easy-to-use-transaction)
  * [IO Extensions](#io-extensions)
    * [Easy to close Stream](#easy-to-close-stream)
    * [Stream doSafe Function](#stream-dosafe-function)
    * [readString/readList](#readstringreadlist)
    * [readString/readList using doSafe](#readstringreadlist-using-dosafe)
    * [writeString/writeList using doSafe](#writestringwritelist-using-dosafe)
    * [File Read and Write](#file-read-and-write)
  * [Handler Extensions](#handler-extensions)
    * [Easy to use Handler](#easy-to-use-handler)
  * [Other Extensions](#other-extensions)
    * [Date Functions](#date-functions)
    * [Number Functions](#number-functions)
    * [String Functions](#string-functions)
    * [Crypto Functions](#crypto-functions)
    * [Check API Level](#check-api-level)
    * [Device Functions](#device-functions)
    * [Preconditions](#preconditions)
  * [Thread Functions](#thread-functions)
    * [Create Thread Pool](#create-thread-pool)
    * [Main Thread Functions](#main-thread-functions)
    * [Context Check](#context-check)
    * [Safe Functions](#safe-functions)
  * [Async Functions](#async-functions)
    * [With Context Check 1](#with-context-check-1)
    * [With Context Check 2](#with-context-check-2)
    * [Without Context Check](#without-context-check)
    * [With Delay](#with-delay)

<!-- toc stop -->


### Context Extensions

#### Activity Functions
```kotlin
// available for Activity
fun activityExtensions() {
    val act = getActivity() // Activity
    act.restart() // restart Activity
    val app = act.getApp() // Application
    val app2 = act.application  // Application
    // Activity.find()
    // Fragment.find()
    // View.find()
    val textView = act.find<TextView>(android.R.id.text1)
}
```

#### Fragment Functions
```kotlin
// available for Fragment
fun fragmentExtensions() {
    val act = activity // Activity
    val app = getApp() // Application
    val textView = find<TextView>(android.R.id.text1) // view.findViewById
    val imageView = find<TextView>(android.R.id.icon1) // view.findViewById
    }
```

#### Easy to use Toast
```kotlin
// available for Context
fun toastExtensions() {
    // available in Activity/Fragment/Service/Context
    toast(R.string.app_name)
    toast("this is a toast")
    longToast(R.string.app_name)
    longToast("this is a long toast")
}
```

#### Easy to Inflate Layout
```kotlin
    // available for Context
    fun inflateLayout() {
        val view1 = inflate(R.layout.activity_main)
        val viewGroup = view1 as ViewGroup
        val view2 = inflate(android.R.layout.activity_list_item, viewGroup, false)
    }
```

#### Useful Functions
```kotlin
// available for Context
fun miscExtensions() {
    val hasCamera = hasCamera()

    mediaScan(Uri.parse("file:///sdcard/Pictures/koi/cat.png"))
    addToMediaStore(File("/sdcard/Pictures/koi/cat.png"))

    val batteryStatusIntent = getBatteryStatus()
    val colorValue = getResourceValue(android.R.color.darker_gray)
}
```

#### Easy to create Intent
```kotlin
// available for Context
fun intentExtensions() {
    val extras = Bundle { putString("key", "value") }
    val intent1 = newIntent<MainActivity>()
    val intent2 = newIntent<MainActivity>(Intent.FLAG_ACTIVITY_NEW_TASK, extras)
}
```

#### Easy to Start Activity
```kotlin
// available for Activity
fun startActivityExtensions() {
    startActivity<MainActivity>()
    // equal to
    startActivity(Intent(this, MainActivity::class.java))

    startActivity<MainActivity>(Intent.FLAG_ACTIVITY_SINGLE_TOP, Bundle())
    startActivity<MainActivity>(Bundle())

    startActivityForResult<MainActivity>(100)
    startActivityForResult<MainActivity>(Bundle(), 100)
    startActivityForResult<MainActivity>(200, Intent.FLAG_ACTIVITY_CLEAR_TOP)
}
```

#### Easy to Start Service
```kotlin
// available for Context
fun startServiceExtensions() {
    startService<BackgroundService>()
    startService<BackgroundService>(Bundle())
}
```

#### Network State
```kotlin
// available for Context
fun networkExtensions() {
    val name = networkTypeName()
    val operator = networkOperator()
    val type = networkType()
    val wifi = isWifi()
    val mobile = isMobile()
    val connected = isConnected()
}
```

#### Notification Builder
```kotlin
// available for Context
fun notificationExtensions() {
    // easy way using Notification.Builder
    val notification = newNotification() {
        this.setColor(0x0099cc)
                .setAutoCancel(true)
                .setContentTitle("Notification Title")
                .setContentText("Notification Message Text")
                .setDefaults(0)
                .setGroup("koi")
                .setVibrate(longArrayOf(1, 0, 0, 1))
                .setSubText("this is a sub title")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setLargeIcon(null)
    }
}
```

#### Package Functions
```kotlin
// available for Context
fun packageExtensions() {
    val isYoutubeInstalled = isAppInstalled("com.google.android.youtube")
    val isMainProcess = isMainProcess()
    val disabled = isComponentDisabled(MainActivity::class.java)
    enableComponent(MainActivity::class.java)

    val sig = getPackageSignature()
    val sigString = getSignature()
    println(dumpSignature())
}
```

#### System Service
```kotlin
// available for Context
// easy way to get system service, no cast
fun systemServices() {
    val wm = getWindowService()
    val tm = getTelephonyManager()
    val nm = getNotificationManager()
    val cm = getConnectivityManager()
    val am = getAccountManager()
    val acm = getActivityManager()
    val alm = getAlarmManager()
    val imm = getInputMethodManager()
    val inflater = getLayoutService()
    val lm = getLocationManager()
    val wifi = getWifiManager()
}
```

#### Easy to Log
```kotlin
// available for Context
fun logExtensions() {
    KoiConfig.logEnabled = true //default is false
    // true == Log.VERBOSE
    // false == Log.ASSERT
    // optional
    KoiConfig.logLevel = Log.VERBOSE // default is Log.ASSERT
    //

    logv("log functions available in Context")  //Log.v
    logd("log functions available in Context")  //Log.d
    loge("log functions available in Context")   //Log.e

    // support lazy evaluated message
    logv { "lazy eval message lambda" }  //Log.v
    logw { "lazy eval message lambda" }  //Log.w
}
```

### View Extensions

#### View Listeners 1
```kotlin
fun viewListeners1() {
    val view = View(this)
    // View.OnClickListener
    view.onClick { print("view clicked") }
    // View.OnLongClickListener
    view.onLongClick { print("view long clicked");false }
    // View.OnKeyListener
    view.onKeyEvent { view, keyCode, event ->
        print("keyEvent: action:${event.action} code:$keyCode")
        false
    }
    // View.OnTouchListener
    view.onTouchEvent { view, event ->
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> print("touch down")
            MotionEvent.ACTION_UP -> print("touch up")
            MotionEvent.ACTION_MOVE -> print("touch move")
        }
        false
    }
    // View.OnFocusChangeListener
    view.onFocusChange { view, hasFocus ->
        print("focus changed = $hasFocus")
    }
}
```

#### View Listeners 2
```kotlin
fun viewListeners2() {
    // TextWatcher
    val editText = EditText(this)
    editText.onTextChange { text, start, before, count ->
        print("text changed: $text")
    }

    // OnCheckedChangeListener
    val checkBox = CheckBox(this)
    checkBox.onCheckedChanged { button, isChecked ->
        print("CheckBox value changed:$isChecked")
    }

    // OnSeekBarChangeListener
    val seekBar = SeekBar(this)
    seekBar.onProgressChanged { seekBar, progress, fromUser ->
        print("seekBar progress: $progress")
    }
}
````

#### ListView Listeners
```kotlin
fun listViewListeners() {
    val listView = ListView(this)
    // OnItemClickListener
    listView.onItemClick { parent, view, position, id ->
        print("onItemClick: position=$position")
    }
    // OnScrollListener
    listView.onScrollChanged { view, scrollState ->
        print("scroll state changed")
    }
}
```

#### View Utils
```kotlin
// available for View
fun viewSample() {
    val w = dm.widthPixels
    val h = dm.heightPixels
    val v1 = 32.5f
    val dp1 = v1.pxToDp()
    val v2 = 24f
    val px1 = v2.dpToPx()
    val dp2 = pxToDp(56)
    val px2 = dpToPx(32)
    val dp3 = 72.pxToDp()
    val px3 = 48.dpToPx()

    hideSoftKeyboard()
    val editText = EditText(context)
    editText.showSoftKeyboard()
    editText.toggleSoftKeyboard()
}
```

### Adapter Extensions

#### Easy to Create Adapter
```kotlin
// easy way to create array adapter
fun adapterFunctions() {
    listView.adapter = quickAdapterOf(
            android.R.layout.simple_list_item_2,
            (1..100).map { "List Item No.$it" })
    { binder, data ->
        binder.setText(android.R.id.text1, data)
        binder.setText(android.R.id.text2, "Index: ${binder.position}")
    }

    val adapter2 = quickAdapterOf<String>(android.R.layout.simple_list_item_1) {
        binder, data ->
        binder.setText(android.R.id.text1, data)
    }
    adapter2.addAll(listOf("Cat", "Dog", "Rabbit"))

    val adapter3 = quickAdapterOf<Int>(android.R.layout.simple_list_item_1,
            arrayOf(1, 2, 3, 4, 5, 6)) {
        binder, data ->
        binder.setText(android.R.id.text1, "Item Number: $data")
    }

    val adapter4 = quickAdapterOf<Int>(android.R.layout.simple_list_item_1,
            setOf(22, 33, 4, 5, 6, 8, 8, 8)) {
        binder, data ->
        binder.setText(android.R.id.text1, "Item Number: $data")
    }
}
```

### Bundle Extensions

#### Bundle Builder
```kotlin
// available in any where
fun bundleExtension() {
    // easy way to create bundle
    val bundle = Bundle {
        putString("key", "value")
        putInt("int", 12345)
        putBoolean("boolean", false)
        putIntArray("intArray", intArrayOf(1, 2, 3, 4, 5))
        putStringArrayList("strings", arrayListOf("Hello", "World", "Cat"))
    }

    // equal to using with
    val bundle2 = Bundle()
    with(bundle2) {
        putString("key", "value")
        putInt("int", 12345)
        putBoolean("boolean", false)
        putIntArray("intArray", intArrayOf(1, 2, 3, 4, 5))
        putStringArrayList("strings", arrayListOf("Hello", "World", "Cat"))
    }
}
```

### Parcelable Extensions

#### Easy to create Parcelable
```kotlin
// easy way to create Android Parcelable class
data class Person(val name: String, val age: Int) : Parcelable {
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(age)
    }

    override fun describeContents(): Int = 0
    protected constructor(p: Parcel) : this(name = p.readString(), age = p.readInt()) {}
    companion object {
        // using createParcel
        @JvmField val CREATOR = createParcel { Person(it) }
    }
}
```

### Collection Extensions

#### Collection to String
```kotlin
fun collectionToString() {
    val pets = listOf<String>("Cat", "Dog", "Rabbit", "Fish")
    // list to string, delimiter is space
    val string1 = pets.asString(delim = " ") // "Cat Dog Rabbit Fish"
    // default delimiter is comma
    val string2 = pets.asString() // "Cat,Dog,Rabbit,Fish"
    val numbers = arrayOf(2016, 2, 2, 20, 57, 40)
    // array to string, default delimiter is comma
    val string3 = numbers.asString() // "2016,2,2,20,57,40"
    // array to string, delimiter is -
    val string4 = numbers.asString(delim = "-") // 2016-2-2-20-57-40
    // using Kotlin stdlib
    val s1 = pets.joinToString()
    val s2 = numbers.joinToString(separator = "-", prefix = "<", postfix = ">")
}
```

#### Map to String
```kotlin
fun mapToString() {
    val map = mapOf<String, Int>(
            "John" to 30,
            "Smith" to 50,
            "Alice" to 22
    )
    // default delimiter is ,
    val string1 = map.asString() // "John=30,Smith=50,Alice=22"
    // using delimiter /
    val string2 = map.asString(delim = "/") // "John=30/Smith=50/Alice=22"
    // using stdlib
    map.asSequence().joinToString { "${it.key}=${it.value}" }
}
```

#### List Append
```kotlin
fun appendAndPrepend() {
    val numbers = (1..6).toArrayList()
    println(numbers.joinToString()) // "1, 2, 3, 4, 5, 6, 7"
    numbers.head() // .dropLast(1)
    numbers.tail() //.drop(1)
    val numbers2 = 100.appendTo(numbers) //
    val numbers3 = 2016.prependTo(numbers)
}
```

### Database Extensions

#### Easy to get Cursor Value
```kotlin
// available for Cursor
fun cursorValueExtensions() {
    val cursor = this.writableDatabase.query("table", null, null, null, null, null, null)
    cursor.moveToFirst()
    do {
        val intVal = cursor.intValue("column-a")
        val stringVal = cursor.stringValue("column-b")
        val longVal = cursor.longValue("column-c")
        val booleanValue = cursor.booleanValue("column-d")
        val doubleValue = cursor.doubleValue("column-e")
        val floatValue = cursor.floatValue("column-f")

        // no need to do like this, so verbose
        cursor.getInt(cursor.getColumnIndexOrThrow("column-a"))
        cursor.getString(cursor.getColumnIndexOrThrow("column-b"))
    } while (cursor.moveToNext())
}
```

#### Easy to convert Cursor to Model
```kotlin
// available for Cursor
// transform cursor to model object
fun cursorToModels() {
    val where = " age>? "
    val whereArgs = arrayOf("20")
    val cursor = this.readableDatabase.query("users", null, where, whereArgs, null, null, null)
    val users1 = cursor.map {
        UserInfo(
                stringValue("name"),
                intValue("age"),
                stringValue("bio"),
                booleanValue("pet_flag"))
    }

    // or using mapAndClose
    val users2 = cursor.mapAndClose {
        UserInfo(
                stringValue("name"),
                intValue("age"),
                stringValue("bio"),
                booleanValue("pet_flag"))
    }

    // or using Cursor?mapTo(collection, transform())
}
```

#### Easy to use Transaction
```kotlin
// available for SQLiteDatabase and SQLiteOpenHelper
// auto apply transaction to db operations
fun inTransaction() {
    val db = this.writableDatabase
    val values = ContentValues()

    // or db.transaction
    transaction {
        db.execSQL("insert into users (?,?,?) (1,2,3)")
        db.insert("users", null, values)
    }
    // equal to
    db.beginTransaction()
    try {
        db.execSQL("insert into users (?,?,?) (1,2,3)")
        db.insert("users", null, values)
        db.setTransactionSuccessful()
    } finally {
        db.endTransaction()
    }
}
```

### IO Extensions

#### Easy to close Stream
```kotlin
// available for Closeable
fun closeableSample() {
    val input = FileInputStream(File("readme.txt"))
    try {
        val string = input.readString("UTF-8")
    } catch(e: IOException) {
        e.printStackTrace()
    } finally {
        input.closeQuietly()
    }
}
```

#### Stream doSafe Function
```kotlin
// simple way, equal to closeableSample
// InputStream.doSafe{}
fun doSafeSample() {
    val input = FileInputStream(File("readme.txt"))
    input.doSafe {
        val string = readString("UTF-8")
    }
}
```

####  readString/readList
```kotlin
// available for InputStream/Reader
fun readStringAndList1() {
    val input = FileInputStream(File("readme.txt"))
    try {
        val reader = input.reader(Encoding.CHARSET_UTF_8)

        val string1 = input.readString(Encoding.UTF_8)
        val string2 = input.readString(Encoding.CHARSET_UTF_8)

        val list1 = input.readList()
        val list2 = input.readList(Encoding.CHARSET_UTF_8)

    } catch(e: IOException) {

    } finally {
        input.closeQuietly()
    }
}
```

#### readString/readList using doSafe
```kotlin
// available for InputStream/Reader
//equal to readStringAndList1
fun readStringAndList2() {
    val input = FileInputStream(File("readme.txt"))
    input.doSafe {
        val reader = reader(Encoding.CHARSET_UTF_8)

        val string1 = readString(Encoding.UTF_8)
        val string2 = readString(Encoding.CHARSET_UTF_8)

        val list1 = readList()
        val list2 = readList(Encoding.CHARSET_UTF_8)
    }
}
```

#### writeString/writeList using doSafe
```kotlin
fun writeStringAndList() {
    val output = FileOutputStream("output.txt")
    output.doSafe {
        output.writeString("hello, world")
        output.writeString("你好，世界", charset = Encoding.CHARSET_UTF_8)

        val list1 = listOf<Int>(1, 2, 3, 4, 5)
        val list2 = (1..8).map { "Item No.$it" }
        output.writeList(list1, charset = Encoding.CHARSET_UTF_8)
        output.writeList(list2)
    }
}
```

#### File Read and Write
```kotlin
fun fileReadWrite() {
    val directory = File("/Users/koi/workspace")
    val file = File("some.txt")

    val text1 = file.readText()
    val text2 = file.readString(Encoding.CHARSET_UTF_8)
    val list1 = file.readList()
    val list2 = file.readLines(Encoding.CHARSET_UTF_8)

    file.writeText("hello, world")
    file.writeList(list1)
    file.writeList(list2, Encoding.CHARSET_UTF_8)

    val v1 = file.relativeToOrNull(directory)
    val v2 = file.toRelativeString(directory)

    // clean files in directory
    directory.clean()


    val file1 = File("a.txt")
    val file2 = File("b.txt")
    file1.copyTo(file2, overwrite = false)
}
```

### Handler Extensions

#### Easy to use Handler
```kotlin
// available for Handler
// short name for functions
fun handlerFunctions() {
    val handler = Handler()
    handler.atNow { print("perform action now") }
    // equal to
    handler.post { print("perform action now") }

    handler.atFront { print("perform action at first") }
    // equal to
    handler.postAtFrontOfQueue { print("perform action at first") }

    handler.atTime(timestamp() + 5000, { print("perform action after 5s") })
    // equal to
    handler.postAtTime({ print("perform action after 5s") }, 5000)

    handler.delayed(3000, { print("perform action after 5s") })
    // equal to
    handler.postDelayed({ print("perform action after 5s") }, 3000)
}
```

### Other Extensions

#### Date Functions
```kotlin
// available in any where
fun dateSample() {
    val nowString = dateNow()
    val date = dateParse("2016-02-02 20:30:45")
    val dateStr1 = date.asString()
    val dateStr2 = date.asString(SimpleDateFormat("yyyyMMdd.HHmmss"))
    val dateStr3 = date.asString("yyyy-MM-dd-HH-mm-ss")

    // easy way to get timestamp
    val timestamp1 = timestamp()
    // equal to
    val timestamp2 = System.currentTimeMillis()
    val dateStr4 = timestamp1.asDateString()
}
```

#### Number Functions
```kotlin
fun numberExtensions() {
    val number = 179325344324902187L
    println(number.readableByteCount())

    val bytes = byteArrayOf(1, 7, 0, 8, 9, 4, 125)
    println(bytes.hexString())
}
```

#### String Functions
```kotlin
// available for String
fun stringExtensions() {
    val string = "hello, little cat!"
    val quotedString = string.quote()
    val isBlank = string.isBlank()
    val hexBytes = string.toHexBytes()
    val s1 = string.trimAllWhitespace()
    val c = string.containsWhitespace()

    val url = "https://github.com/mcxiaoke/kotlin-koi?year=2016&encoding=utf8&a=b#changelog"
    val urlNoQuery = url.withoutQuery()

    val isNameSafe = url.isNameSafe()
    val fileName = url.toSafeFileName()
    val queries = url.toQueries()

    val path = "/Users/koi/workspace/String.kt"
    val baseName = path.fileNameWithoutExtension()
    val extension = path.fileExtension()
    val name = path.fileName()
}
```

#### Crypto Functions
```kotlin
// available in any where
fun cryptoFunctions() {
    val md5 = HASH.md5("hello, world")
    val sha1 = HASH.sha1("hello, world")
    val sha256 = HASH.sha256("hello, world")
}
```

#### Check API Level
```kotlin
// available in any where
fun apiLevelFunctions() {
    // Build.VERSION.SDK_INT
    val v = currentVersion()
    val ics = icsOrNewer()
    val kk = kitkatOrNewer()
    val bkk = beforeKitkat()
    val lol = lollipopOrNewer()
    val mar = marshmallowOrNewer()
}
```

#### Device Functions
```kotlin
// available in any where
fun deviceSample() {
    val a = isLargeHeap
    val b = noSdcard()
    val c = noFreeSpace(needSize = 10 * 1024 * 1024L)
    val d = freeSpace()
}
```

#### Preconditions
```kotlin
// available in any where
// null and empty check
fun preconditions() {
    throwIfEmpty(listOf(), "collection is null or empty")
    throwIfNull(null, "object is null")
    throwIfTrue(currentVersion() == 10, "result is true")
    throwIfFalse(currentVersion() < 4, "result is false")
}
```

###Thread Functions

#### Create Thread Pool
```kotlin
// available in any where
fun executorFunctions() {
    // global main handler
    val uiHandler1 = CoreExecutor.mainHandler
    // or using this function
    val uiHandler2 = koiHandler()

    // global executor service
    val executor = CoreExecutor.executor
    // or using this function
    val executor2 = koiExecutor()

    // create thread pool functions
    val pool1 = newCachedThreadPool("cached")
    val pool2 = newFixedThreadPool("fixed", 4)
    val pool3 = newSingleThreadExecutor("single")
}
```

#### Main Thread Functions
```kotlin
// available in any where
fun mainThreadFunctions() {
    //check current thread
    // call from any where
    val isMain = isMainThread()

    // execute in main thread
    mainThread {
        print("${(1..8).asSequence().joinToString()}")
    }

    // delay execute in main thread
    mainThreadDelay(3000) {
        print("execute after 3000 ms")
    }
}
```

#### Context Check
```kotlin

    // isContextAlive function impl
    fun <T> isContextAlive(context: T?): Boolean {
        return when (context) {
            null -> false
            is Activity -> !context.isFinishing
            is Fragment -> context.isAdded
            is android.support.v4.app.Fragment -> context.isAdded
            is Detachable -> !context.isDetached()
            else -> true
        }
    }
```

#### Safe Functions
```kotlin
// available in any where
fun safeFunctions() {
    val context = this
    // check Activity/Fragment lifecycle
    val alive = isContextAlive(context)

    fun func1() {
        print("func1")
    }
    // convert to safe function with context check
    // internal using  isContextAlive
    val safeFun1 = safeFunction(::func1)

    // call function with context check
    // internal using isContextAlive
    safeExecute(::func1)

    // direct use
    safeExecute { print("func1") }
}
```

### Async Functions

```kotlin
class AsyncFunctionsSample {
    private val intVal = 1000
    private var strVal: String? = null
}
```

#### With Context Check 1
```kotlin
// async functions with context check
// internal using isContextAlive
// context alive:
// !Activity.isFinishing
// Fragment.isAdded
// !Detachable.isDetached
//
// available in any where
// using in Activity/Fragment better
fun asyncSafeFunction1() {
    // safe means context alive check
    // async
    asyncSafe {
        print("action executed only if context alive ")
        // if you want get caller context
        // maybe null
        val ctx = getCtx()
        // you can also using outside variables
        // not recommended
        // if context is Activity or Fragment
        // may cause memory leak
        print("outside value, $intVal $strVal")

        // you can using mainThreadSafe here
        // like a callback
        mainThreadSafe {
            // also with context alive check
            // if context dead, not executed
            print("code here executed in main thread")
        }
        // if you don't want context check, using mainThread{}
        mainThread {
            // no context check
            print("code here executed in main thread")
        }
    }
    // if your result or error is nullable
    // using asyncSafe2, just as asyncSafe
    // but type of result and error is T?, Throwable?
}
```

#### With Context Check 2
```kotlin
fun asyncSafeFunction2() {

    // async with callback
    asyncSafe(
            {
                print("action executed in async thread")
                listOf<Int>(1, 2, 3, 4, 5)
            },
            { result, error ->
                // in main thread
                print("callback executed in main thread")
            })
}
```

```kotlin
fun asyncSafeFunction3() {
    // async with success/failure callback
    asyncSafe(
            {
                print("action executed in async thread")
                "this string is result of the action"
                // throw RuntimeException("action error")
            },
            { result ->
                // if action success with no exception
                print("success callback in main thread result:$result")
            },
            { error ->
                // if action failed with exception
                print("failure callback in main thread, error:$error")
            })
}
```

#### Without Context Check
```kotlin
// if you don't want context check
// using asyncUnsafe series functions
// just replace asyncSafe with asyncUnsafe
fun asyncUnsafeFunctions() {
    // async
    asyncUnsafe {
        print("action executed with no context check ")
        // may cause memory leak
        print("outside value, $intVal $strVal")

        mainThread {
            // no context check
            print("code here executed in main thread")
        }
    }
}
```

#### With Delay
```kotlin
// async functions with delay
// with context check
// if context died, not executed
// others just like asyncSafe
fun asyncDelayFunctions() {
    // usage see asyncSafe
    asyncDelay(5000) {
        print("action executed after 5000ms only if context alive ")

        // you can using mainThreadSafe here
        // like a callback
        mainThreadSafe {
            // also with context alive check
            // if context dead, not executed
            print("code here executed in main thread")
        }
        // if you don't want context check, using mainThread{}
        mainThread {
            // no context check
            print("code here executed in main thread")
        }
    }
}
```

### About Me

#### Contacts

* Blog: <http://blog.mcxiaoke.com>
* Github: <https://github.com/mcxiaoke>
* Email: [github@mcxiaoke.com](mailto:github@mcxiaoke.com)

#### Projects

* awesome-kotlin: <https://github.com/mcxiaoke/awesome-kotlin>
* Android-Next: <https://github.com/mcxiaoke/Android-Next>
* PackerNg: <https://github.com/mcxiaoke/packer-ng-plugin>
* gradle-packer-plugin: <https://github.com/mcxiaoke/gradle-packer-plugin>
* xBus: <https://github.com/mcxiaoke/xBus>
* ReacitveX Docs: <https://github.com/mcxiaoke/RxDocs>
* MQTT Translation: <https://github.com/mcxiaoke/mqtt>
* Fanfou App: <https://github.com/mcxiaoke/minicat>
* Fanfou Opensource: <https://github.com/mcxiaoke/fanfouapp-opensource>
* Volley Mirror: <https://github.com/mcxiaoke/android-volley>

------

### License

    Copyright 2015, 2016 Xiaoke Zhang

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.