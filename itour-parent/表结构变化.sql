MYSQL备份语法
mysqldump -h 主机 -u用户名 -p 密码 数据库 表明 > 物理磁盘位置/文件名
备份单表
mysqldump -hlocalhost -uroot -p123456 school student >d:/student.sql
备份数据库

备份多表
MYSQL导入sql文件
source 备份文件
mysql -u用户名 -p密码 库名< 备份文件



备份:
mysqldump -u用户名 -p密码 库名 >备份文件
mysqldump -uroot -ptop@958958 itour>backup.sql
导入：
mysql -u用户名 -p密码 库名<备份文件
mysql -uroot -ptop@958958 itour<backup.sql


change master to　
master_host='1.116.226.147',master_user='ben_1',master_password='ben_1@958958',master_port=3306,master_log_file='mysql-bin.000005',master_log_pos=72170102,master_connect_retry=30,master_heartbeat_period=10;

change master to　master_host='1.116.226.147',master_user='ben',  master_password='ben@958958',　 master_port=3308, master_log_file='binlog.000025', master_log_pos=134954, master_connect_retry=30,　 master_heartbeat_period=10;