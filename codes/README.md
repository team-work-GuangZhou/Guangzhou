**存放项目所有源代码**

-----------------------
# 代码规范

代码规范的作用

*以下文字摘自《Clean Code(代码整洁之道)》*

你或许会问：代码真正“读”的成分有多少呢？难道力量主要不是用在“写”上吗？你是否玩过“编辑器回放”？
20世纪80、90年代，Emacs之类编辑器记录每次击键动作。你可以在一小时工作之后 ，回放击键过程，就像是看一部告诉电影。我这么做过，结果很有趣。

回放过程显示，多数时间都是在滚动屏幕浏览其他模块！

　　鲍勃进入模块。
　　他向下滚动到要修改的函数。
　　他停下来考虑可以做什么。
　　哦，他滚动到模块顶端，检查变量的初始化。
　　现在他回到修改处，开始键入。
　　喔，他删掉了键入的内容。
　　他重新键入。
　　他又删除了！
　　他键入了一半什么东西，又开始删除。
　　他滚动到调用要修改函数的另一函数，看看是怎么调用的。
　　他回到修改处，修改键入刚才删掉的代码。
　　他停下来。
　　他再一次删掉代码！
　　他打开另一个窗口，查看别的子类。那是个负载函数吗？
　　……

你该明白了。读与写花费时间的比例超过10:1。写新代码时，我们一直在读旧代码。
对于大多数人来说，读代码的时间比写代码要多很多。而对于团队工作的我们来说，如果使用不同代码风格会对可读性造成更大的打击。因此，所有新代码必须符合代码规范，同时逐步修改遗留在老代码中的不符合规范的部分。

## Android代码规范
Java规范
Java的代码规范以1997年Sun公司提出的Java Code Conventions，稍加修改后完成。

- **命名规范**
包名全小写
package com.cootek.smartdialer;
类名为Pascal命名法
public class ModelManager {}
接口名按照类名的要求，加前缀"I"
public interface IListener {}
常量使用全大写下划线分隔
public static final int MAX_COUNT = 100;
public/protected变量使用驼峰法
public int mainSize;
private静态变量增加s前缀 同时避免使用public静态变量
private static int sCount;
private成员变量增加m前缀
private int mTotal;
局部变量使用驼峰法
int currentSize;
函数使用驼峰法
public void getList() {}
- **缩进规范**
每行长度尽可能保持在笔记本的2/3，显示屏的一半以内，以方便代码读写。

标准缩进为4空格，同一语句换行缩进为8空格，代码中不允许出现<Tab>。

if/while等条件的折行需注意||或者&&应该在第二行。

public boolean process(boolean flag, int count) {
    if (flag && mCount < MAX) {
        mCount = mCount + count * 1.5 
                + (count > 0 ? count * count : count / 2);
        flag &= mCount > 0;
    }
    return flag;
}
- **注释规范**
对于//或者/* */ 的注释没有特别要求。 在代码中，要么用常量，要么使用magic number并加上注释说明。

使用/** */作为javadoc注释，需要在针对工具类的public接口加以说明，以便他人使用。

TODO注释用来表示还有需要后续处理的事务。

//TODO [Thomas]This code can only process at most 50 call logs.
禁止使用注释的形式来保留不再使用的代码，除非通过注释写明必须这么做的理由。

- **变量声明规范**
一行一个变量

int size;
long time;
可以将同类型语义接近的变量声明在一行中

int height, width;
变量声明需要注意作用域，尽可能缩小变量的作用域，例如

public int process(boolean flag) {
    int a = 0;
    if (flag) {
        a += 2;
        int b = a / 3;
        a -= b;
    } else {
        a += 1;
    }
    return a;
}
在for语句中的特殊写法是，我们会把循环变量的声明放在for语句中，例如

for (int i = 0; i < 10; i++) {}

int[] array = new int[10];
for (int i : array) {}
变量需要初始化，类成员变量的初始化放在构造函数或者静态构造函数中，局部变量应在声明时初始化

- **空格使用规范**
if/while/do-while/switch/for语句的括号外侧左右都需要空格，内侧不空格。函数括号外侧不空格。

一元运算符不加空格，二元、三元运算符前后加空格。逗号后空一格。

a++;
a += 1;
a = a + 1;
if (a == b) {}
while (a < b) {}
process(a, b);
- **语句使用规范**
不在一行中使用多个语句，不使用","分割语句。

a++;
b--;
if语句
只有将if语句写在一行的时候，才可以允许没有花括号

if （obj != null) return;
if...else if...else的标准格式如下

if (a < 0) {
    b = -1;
} else if (a > 0) {
    b = 1;
} else {
    b = 0;
}
请注意空格的使用。

for语句
for语句需要注意将循环变量定义在语句中

for (int i = 0; i < 100; i++) {
    sum += i;
}
请注意空格的使用。

while语句
while (++i <= 100) {
    sum += i;
}
do-while语句
do {
    sum += i;
} while (++i <= 100);
switch语句
如果有fall through的case块，必须用注释明确注明fall through

switch (type) {
case A:
    a++;
    /*fall through*/
case B:
case C:
    d--;
    break;
default:
    e++;
    break;
}
try-catch语句
InputStream is = null;
try {
    is = new FileInputStream(new File("file"));
    // read is;
} catch (IOException e) {
    e.printStackTrace();
} finally {
    try {
        if (is != null) is.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
针对boolean值的判断
在if/while等语句中，需要提供判断条件，很多时候判断参数是boolean值时，请注意不要

if (flag == true) {} // AVOID!
应当使用

if (flag) {}
if (!flag) {}

## iOS代码规范
Objective-C规范
Objective-C通过代码Sample整理得出

- **命名规范**
@interface为Pascal命名法

@interface Node : NSObject
// definition of Node
@end
常量使用全大写下划线分割

#define MAX_COUNT (100)
枚举类型使用Pascal命名法

typedef enum {
    CallLogIncomingType = 0,
    CallLogOutgoingType = 1,
    CallLogIncomingMissedType = 2,
}CallLogType;
@property使用驼峰法，必须声明copy/assign; nonatomic/atomic; readonly(必须)/readwrite(可以省略)

@property (nonatomic, retain) UIView *contentView;
@property (atomic, assign) BOOL isHighlight;
同时使用@synthesize时，增加结尾下划线以区分property和member

@synthesize abc = abc_
静态变量使用Pascal法并最后加下划线

TPSomeManager *Instance_;
局部变量使用驼峰法

int currentSize;
函数使用驼峰法

- (void)doWorkWithString:(NSString *)name;
- **缩进规范**
每行长度不要超过120个字符，请在XCode中设置提示线。

Xcode > Preferences > Text Editing > Page guide at column，填入120。
标准缩进为4空格，代码中不允许出现<Tab>。

换行后的缩进使用XCode默认设置。

if/while等条件的折行需注意||或者&&应该在上行的行尾。

函数的折行需注意冒号对齐(XCode默认设置)

- (BOOL)calculateAndGetSign:(int)count
{
    return count > 0;
}

- (void)findIndexInString:(NSString *)word
                character:(char)aCharacter
{

}
- **注释规范**
对于//或者/* */ 的注释没有特别要求。 在代码中，要么用常量，要么使用magic number并加上注释说明。

TODO注释用来表示还有需要后续处理的事务。

//TODO [Thomas]:This code can only process at most 50 call logs.
禁止使用注释的形式来保留不再使用的代码，除非通过注释写明必须这么做的理由。

- **空格使用规范**
if/while/do-while/switch/for语句的括号外侧左右都需要空格，内侧不空格。

c函数括号外侧不空格。objc函数嵌套须增加空格。

一元运算符不加空格，二元、三元运算符前后加空格。逗号后空一格。

a++;
a += 1;
a = a + 1;
if (a == b) {}
while (a < b) {}
process(a, b);
[[this name] length];
- **花括号使用规范**
方法实现的花括号独立成行。

而其余情形，左括号位于上一行行尾。

- (void) test:(int)a
{
    if (a > 0) {
        a++;
    } else if (a < 0) {
        a--;
    } else {
        // doing nothing
    }

    while (a < 10) {
        a++;
    }
}

-----------------------------------
