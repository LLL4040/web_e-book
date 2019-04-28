use ebook;
/*
insert into books values ("数据库系统概论", "（美）Abraham Silberschatz / （美）Henry F.Korth / （美）S.Sudarshan ", "../static/database.jpg", "9787111375296",
99, 69.5, "本书是数据库系统方面的经典教材之一，其内容由浅入深，既包含数据库系统基本概念，又反映数据库技术新进展。它被国际上许多著名大学所采用，包括斯坦福大学、耶鲁大学、得克萨斯大学、康奈尔大学、伊利诺伊大学等。我国也有多所大学采用本书作为本科生和研究生数据库课程的教材和主要教学参考书，收到了良好的效果。
第6版保持了前5版的总体风格，同时对内容进行了扩充，对结构进行了调整，以更好地符合数据库教学的需求和反映数据库设计、管理与使用方式的发展和变化。
具体更新内容如下：
调整了内容组织结构，将SQL内容提前，并集中进行介绍。
采用一个新的模式（基于大学的数据）作为贯穿全书的运行实例。
修订和更新了对数据存储、索引和查询优化以及分布式数据库的涵盖。
修订了E-R模型、关系设计和事务管理等内容。
扩充了关于应用开发和安全性的素材。", 
"Abraham Silberschatz 于纽约州立大学石溪分校获得博士学位，现为耶鲁大学计算机科学Sidney J. Weinberg教授，计算机科学系主任，曾任贝尔实验室信息科学研究中心副主任。他是ACM Fellow 和 IEEE Fellow，曾获得IEEE Taylor L. Booth 教育奖、 ACM Karl V. Karlstrom 杰出教育者奖、ACM SIGMOD 贡献奖和IEEE 计算机学会杰出论文奖。他的研究兴趣包括操作系统、数据库系统、存储系统、网络管理和分布式系统。
Henry F. Korth 于普林斯顿大学获得博士学位，现为利哈伊大学计算机科学与工程系Weiseman教授，曾任贝尔实验室数据库原理研究中心主任。他是ACM Fellow 和 IEEE Fellow，是VLDB 10年贡献奖的获得者。他的研究兴趣包括为现代计算架构（多核、多线程、多级缓存）设计的数据库算法、基于Web的大型数据仓储、实时数据库系统和并行系统。
S. Sudarshan 于威斯康星大学麦迪逊分校获得博士学位，现为印度理工学院计算机科学与工程系教授，曾为贝尔实验室数据库研究组技术人员。他的研究兴趣包括查询处理和优化、关系数据和图结构数据的关键字查询，以及构建和测试数据库应用系统的工具。");
insert into books values ("深入理解计算机系统", " Randal E.Bryant / David O'Hallaron", "../static/ics.jpg", "9787111544937",
99, 139, "和第2版相比，本版内容上最大的变化是，从以IA32和x86-64为基础转变为完全以x86-64为基础。
主要更新如下：
基于x86-64，大量地重写代码，首次介绍对处理浮点数据的程序的机器级支持。
处理器体系结构修改为支持64位字和操作的设计。
引入更多的功能单元和更复杂的控制逻辑，使基于程序数据流表示的程序性能模型预测更加可靠。
扩充关于用GOT和PLT创建与位置无关代码的讨论，描述了更加强大的链接技术（比如库打桩）。
增加了对信号处理程序更细致的描述，包括异步信号安全的函数等。
采用新函数，更新了与协议无关和线程安全的网络编程。", 
"Randal E. Bryant，1981年于麻省理工学院获得计算机博士学位，1984年至今一直任教于卡内基-梅隆大学。现任卡内基-梅隆大学计算机科学学院院长、教授，同时还受邀任教于电子和计算机工程系。他从事本科生和研究生计算机系统方面课程的教学近40年。他和O’Hallaron教授一起在卡内基梅隆大学开设了15-213课程“计算机系统导论”，那便是本书的基础。他还是ACM院士、IEEE院士、美国国家工程院院士和美国人文与科学研究院院士。其研究成果被Intel、IBM、Fujitsu和Microsoft等主要计算机制造商使用，他还因研究获得过Semiconductor Research Corporation、ACM、IEEE颁发的多项大奖。
David R. O’Hallaron，卡内基梅隆大学电子和计算机工程系教授。在弗吉尼亚大学（University of Virginia）获得计算机科学的博士学位，2007年-2010年为Intel匹兹堡实验室主任。他教授本科生和研究生的计算机系统方面的课程已有20余年，并和Bryant教授一起开设了“计算机系统导论”课程。曾获得CMU计算机学院颁发的Herbert Simon杰出教学奖。他主要从事计算机系统领域的研究，与Quake项目成员一起获得过高性能计算领域中的*高国际奖项——Gordon Bell奖。他目前的工作重点是研究自动分级（autograding）概念，即评价其他程序质量的程序。");

insert into books values("神经网络与机器学习", "[加] Simon Haykin", "../static/nurvenet.jpg", "9787111324133", 99, 79,
"神经网络是计算智能和机器学习的重要分支，在诸多领域都取得了很大的成功。在众多神经网络著作中，影响最为广泛的是Simon Haykin的《神经网络原理》（第3版更名为《神经网络与机器学习》）。在本书中，作者结合近年来神经网络和机器学习的最新进展，从理论和实际应用出发，全面、系统地介绍了神经网络的基本模型、方法和技术，并将神经网络和机器学习有机地结合在一起。
本书不但注重对数学分析方法和理论的探讨，而且也非常关注神经网络在模式识别、信号处理以及控制系统等实际工程问题的应用。本书的可读性非常强，作者举重若轻地对神经网络的基本模型和主要学习理论进行了深入探讨和分析，通过大量的试验报告、例题和习题来帮助读者更好地学习神经网络。",
"Simon Haykin 于1953年获得英国伯明翰大学博士学位，目前为加拿大McMaster大学电子与计算机工程系教授、通信研究实验室主任。他是国际电子电气工程界的著名学者，曾获得IEEE McNaughton金奖。他是加拿大皇家学会院士、IEEE会士，在神经网络、通信、自适应滤波器等领域成果颇丰，著有多部标准教材。");
*/
/*
insert into users values ("test", "123", "123@qq.com", 1, 0);

insert into carts values ("test", "9787111375296", 1);
*/
/*
delete from orders where username = 'test';

delete from orderitems where item_id = 1;
select username, cover, bookname, ISBN, price, num
from carts natural join books
where username = 'test';
*/
/*
select order_id, username, ISBN, bookname, cover, price, number, time
from users natural join orders natural join orderitems natural join books 
where username = 'test0';
*/
insert into users values("test1", "123", "123@qq.com", 0, 0);