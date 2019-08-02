package com.gcrobot.util;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Global {

    /**
     * 设置要发送的命令
     */
    enum cmdType {
        CMD_FUNCTION_BASE(0x6100),
        CMD_FUNCTION_LOGIN(0x6101),             //登录命令, 后面为LoginInfo。
        CMD_FUNCTION_LOGOUT(0x6102),           //退出命令。
        CMD_FUNCTION_HEARTBEAT(0x6103),         //心跳。
        CMD_FUNCTION_ADDUSERINFO(0x6104),      //增加用户。
        CMD_FUNCTION_MODIFYUSERINFO(0x6105),   //修改用户。
        CMD_FUNCTION_DELETEUSERINFO(0x6106),    //删除用户。
        CMD_FUNCTION_ADDROBOTINFO(0x6107),     //增加机器人账号。
        CMD_FUNCTION_MODIFYROBOTINFO(0x6108),  //修改机器人账号。
        CMD_FUNCTION_DELETEROBOTINFO(0x6109),   //删除机器人账号。
        CMD_FUNCTION_GETUSERINFO(0x6110),       //获取用户信息。
        CMD_FUNCTION_GETROBOTINFO(0x6111),     //获取机器人信息
        CMD_FUNCTION_GETROBOTSTATUS(0x6112),   //获取机器人在线状态
        CMD_FUNCTION_ROBOTCHANGESTATUS(0x6113),  //机器人上下线
        CMD_FUNCTION_URL(0x6114),                 //视频文件的http地址
        CMD_FUNCTION_MESSAGE(0x6115),            //报警消息
        CMD_FUNCTION_ROBOTPARAM(0x6116),        //机器人参数（速度、位置、电量等）
        CMD_FUNCTION_SEARCHVIDEOINFO(0x6117),    //查询视频文件
        CMD_FUNCTION_SEARCHMESSAGEINFO(0x6118),    //查询报警消息
        CMD_FUNCTION_DIRECTION(0x6119),             //行驶方向
        CMD_FUNCTION_CAMERADIRECTION(0x6120),      //云台转向
        CMD_FUNCTION_LIGHT(0x6121),                 //灯光
        CMD_FUNCTION_WIPER(0x6122),                 //雨刷
        CMD_FUNCTION_LIGHTRESULT(0x6123),            // CMD_FUNCTION_LIGHT结果
        CMD_FUNCTION_WIPERRESULT(0x6124),           // CMD_FUNCTION_WIPER结果
        CMD_FUNCTION_GETROBOTCAMERAINFO(0x6125),  //获取云台信息
        CMD_FUNCTION_SETROBOTCAMERAINFO(0x6126),  //设置云台信息
        CMD_FUNCTION_SETROBOTINSPECTIONINFO_ROBOT(0x6127),  //机器人端设置巡检状态（自动：手动）
        CMD_FUNCTION_SETROBOTINSPECTIONINFO_CLIENT(0x6128),  //PC端设置巡检状态（自动：手动）
        CMD_FUNCTION_GETROBOTINSPECTIONINFO(0x6129),  //获取巡检状态（自动：手动）
        CMD_FUNCTION_SETROBOTINSPECTIONINFORESULT(0x6130),  //设置状态结果
        CMD_FUNCTION_ADDROBOTPOSITIONINFO(0x6131),  //增加巡检预置点
        CMD_FUNCTION_MODIFYROBOTPOSITIONINFO(0x6132),  //修改巡检预置点
        CMD_FUNCTION_DELETEROBOTPOSITIONINFO(0x6133),  //删除巡检预置点
        CMD_FUNCTION_GETROBOTPOSITIONINFO(0x6134),  //获取巡检预置点
        CMD_FUNCTION_P2PAUDIOREQUEST(0x6135),  //语音对讲请求
        CMD_FUNCTION_P2PAUDIOREQUESTRESULT(0x6136),  //语音对讲请求结果
        CMD_FUNCTION_RETURN(0x6137),  //一键返航
        CMD_FUNCTION_ADDTAKSINFO(0x6138),  //添加巡检任务
        CMD_FUNCTION_MODIFYTASKINFO(0x6139),  //修改巡检任务
        CMD_FUNCTION_DELETETASKINFO(0x6140),  //删除巡检任务
        CMD_FUNCTION_SEARCHTASKINFO(0x6141),  //查询巡检任务
        CMD_FUNCTION_GETTODAYTASKINFO(0x6142),  //获取当天巡检任务
        CMD_FUNCTION_BEGINTASK(0x6143),      //开始执行任务（机器人通知服务器）
        CMD_FUNCTION_COMPLETETASK(0x6144), //任务执行完毕（服务器通知机器人）
        CMD_FUNCTION_DISOBEYCHECK(0x6145),   //违停检测（业务服务器通知图像处理）
        CMD_FUNCTION_DISOBEYCHECKCOMPLETE(0x6146), //违停检测结束（图像处理通知业务服务器）
        CMD_FUNCTION_RTMPLOGIN(0x6147),   //视频处理长连接登录
        CMD_FUNCTION_RTMPLOGOUT(0x6148); //视频处理长连接退出

        private final int value;

        private cmdType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static final int MAX_ACCOUNT_LENGTH = 33;//设置用户名最大为32个字符
    public static final int MAX_LOCATION_LENGTH = 33;//设置用户真名最大为32个字符
    public static final int MD5_PASSWORD_LENGTH = 33;//设置md5加密后的密码的最大事32个字符
    public static final int MAX_URLINFO_LENGTH = 50;//设置登录时返回的urlinfo数组最大为50个
    public static final int SIZE_OF_PACKET_HEAD = 12;//记录head数据结构头的byte数组长度为12个字节
    public static final int ROBOTINFO_LENGTH=101;//记录机器人信息返回的单个结构体长度
    public static final int ROBOTSTATUS_LENGTH=37;//记录机器人状态信息返回的单个结构体长度
    public static final int SETORGETSPECTIONINDO=4;//发送 获取和设置 机器人状态信息pack长度
    public static final int ROBOTDIRECTION_LENGTH=4;//机器人移动和云台移动pack的长度

    public static final int HEAD_nSN = 0x8eb69af7;//设置head中的需要的参数值nSN

    //-------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 将value 转成2字节  添加到b数组的offset offset+1 位置
     */
    public static void htons(byte[] b, int offset, int value) {
        b[offset + 1] = (byte) (value >> 8);
        b[offset] = (byte) value;
    }

    /**
     * 将value 转成4字节  添加到b数组的offset offset+1 offset+2 offset+3位置
     */
    public static void htonl(byte[] b, int offset, int value) {
        b[offset + 3] = (byte) (value >> 24 & 0xff);
        b[offset + 2] = (byte) (value >> 16 & 0xff);
        b[offset + 1] = (byte) (value >> 8 & 0xff);
        b[offset] = (byte) (value & 0xff);
    }

    /**
     * 将数据转化为byte[] 的主控制方法
     */
    public static void PacketHead(int nSN, int nUserID, int nType, int nPackLength, cmdType cmd
            , byte[] data, int offset
    ) {
        htonl(data, offset, nSN);
        htons(data, offset + 4, nUserID);
        htons(data, offset + 6, nType);
        htons(data, offset + 8, nPackLength);
        htons(data, offset + 10, cmd.getValue());
    }

    /**
     * 将char[] 转成Byte[]
     **/
    public static byte[] getBytes(char[] chars) {

        byte[] bb = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            byte b = (byte) chars[i];
            bb[i] = b;
        }

        System.out.println(bb.length);
        return bb;
    }


    /**
     * 把2个字节的byte转成  short或int也可以接收
     */
    public static short ntohs(byte[] b, int index) {
        return (short) (((b[index + 1] << 8) | b[index] & 0xff));
    }

    /**
     * 把4个字节的byte转成  int或long也可以接收
     **/
    public static int ntohl(byte[] b, int offset) {
        int addr = 0;
        addr = b[offset] & 0xFF;
        addr |= ((b[offset + 1] << 8) & 0xFF00);
        addr |= ((b[offset + 2] << 16) & 0xFF0000);
        addr |= ((b[offset + 3] << 24) & 0xFF000000);
        return addr;
    }

    /**
     * byte数组转成String类型
     **/

    public static String getString(byte[] b, int offset, int length) {

        byte[] bb = new byte[length];
        System.arraycopy(b, offset, bb, 0, length);

        String ss = new String(bb);

        char[] chars = ss.toCharArray();

        StringBuffer str = new StringBuffer();
        for (char c : chars) {

            Character ch = c;
            if (0 == ch.hashCode()) {
                break;
            } else {
                str.append(c);
            }
        }

        return str.toString();
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 封装登录数据
     */
    public static byte[] loginInfo(String account, String password, String location) {
        int offset = 0;

        int PackLength = SIZE_OF_PACKET_HEAD + (Global.MAX_ACCOUNT_LENGTH + Global.MD5_PASSWORD_LENGTH + Global.MAX_LOCATION_LENGTH + 2);

        byte[] pkt = new byte[PackLength];

        PacketHead(HEAD_nSN, 0, 2, PackLength, cmdType.CMD_FUNCTION_LOGIN, pkt, 0);
        offset += SIZE_OF_PACKET_HEAD;
        byte[] accountData = Global.getBytes(account.toCharArray());
        byte[] passwordData = Global.getBytes(password.toCharArray());

        htons(pkt, offset, 0);
        offset += 2;

        System.arraycopy(accountData, 0, pkt, offset, accountData.length);
        offset += MAX_ACCOUNT_LENGTH;
        System.arraycopy(passwordData, 0, pkt, offset, passwordData.length);
        offset += MD5_PASSWORD_LENGTH;

        if ((location != null) && (location.length() > 0) && (location.length() < Global.MAX_LOCATION_LENGTH)) {
            byte[] locationData = Global.getBytes(location.toCharArray());
            System.arraycopy(locationData, 0, pkt, offset, locationData.length);
        }

        return pkt;
    }

    /**
     * 封装心跳包数据
     */
    public static byte[] beatPack(int uid) {
        byte[] bytes = new byte[Global.SIZE_OF_PACKET_HEAD];
        PacketHead(HEAD_nSN, uid, 2, Global.SIZE_OF_PACKET_HEAD, cmdType.CMD_FUNCTION_HEARTBEAT, bytes, 0);
        return bytes;
    }

    /**
     * 封装退出命令数据
     */
    public static byte[] logout(int uid) {

        byte[] bytes = new byte[Global.SIZE_OF_PACKET_HEAD];

        PacketHead(HEAD_nSN, uid, 2, Global.SIZE_OF_PACKET_HEAD, cmdType.CMD_FUNCTION_LOGOUT, bytes, 0);

        return bytes;
    }

    /**
     * 封装获取机器人信息的命令
     */
    public static byte[] robotmeg(int uid) {
        byte[] bytes = new byte[Global.SIZE_OF_PACKET_HEAD];

        PacketHead(HEAD_nSN, uid, 2, Global.SIZE_OF_PACKET_HEAD, cmdType.CMD_FUNCTION_GETROBOTINFO, bytes, 0);

        return bytes;
    }

    /**
     * 封装获取机器人在线状态的命令
     */
    public static byte[] robotstatus(int uid) {

        byte[] bytes = new byte[Global.SIZE_OF_PACKET_HEAD];

        PacketHead(HEAD_nSN, uid, 2, Global.SIZE_OF_PACKET_HEAD, cmdType.CMD_FUNCTION_GETROBOTSTATUS, bytes, 0);

        return bytes;
    }
    /**
     * 封装机器人移动命令数据
     * */

    public static byte[] robotdirection(int uid,int rid,int driection){
        int offset = 0;
        int PackLength=Global.SIZE_OF_PACKET_HEAD+Global.ROBOTDIRECTION_LENGTH;
        byte[] pkt = new byte[PackLength];
        PacketHead(HEAD_nSN, uid, 2, PackLength, cmdType.CMD_FUNCTION_DIRECTION, pkt, offset);
        offset+=Global.SIZE_OF_PACKET_HEAD;

        htons(pkt, offset, rid);
        offset += 2;
        htons(pkt,offset,driection);

        return pkt;
    }
    /**
     *  封装机器人云台转向
     * */
    public static byte[] robotcamera(int uid,int rid,int driection){
        int offset = 0;
        int PackLength=Global.SIZE_OF_PACKET_HEAD+ROBOTDIRECTION_LENGTH;
        byte[] pkt = new byte[PackLength];
        PacketHead(HEAD_nSN, uid, 2, PackLength, cmdType.CMD_FUNCTION_CAMERADIRECTION, pkt, offset);
        offset+=Global.SIZE_OF_PACKET_HEAD;

        htons(pkt, offset, rid);
        offset += 2;
        htons(pkt,offset,driection);

        return pkt;
    }

    /**
     * 封装获取巡检状态
     * */
    public static byte[] getrobotinspectioninfo(int uid,int rid){
        int offset=0;
        int PackLength=Global.SIZE_OF_PACKET_HEAD+Global.SETORGETSPECTIONINDO;
        byte[] bytes = new byte[PackLength];
        PacketHead(HEAD_nSN, uid, 2, PackLength,cmdType.CMD_FUNCTION_GETROBOTINSPECTIONINFO,bytes, offset);
        offset+=Global.SIZE_OF_PACKET_HEAD;
        htons(bytes, offset, rid);
        offset+=2;
        htons(bytes,offset,0);

        return bytes;
    }
    /**
     * 封装设置巡检状态
     * */
    public static byte[] setrobotinspectioninfo(int uid,int rid,int status){
        int offset=0;
        int PackLength=Global.SIZE_OF_PACKET_HEAD+4;

        byte[] bytes = new byte[Global.SIZE_OF_PACKET_HEAD+Global.SETORGETSPECTIONINDO];
        PacketHead(HEAD_nSN, uid, 2, PackLength,cmdType.CMD_FUNCTION_SETROBOTINSPECTIONINFO_CLIENT,bytes, offset);
        offset+=Global.SIZE_OF_PACKET_HEAD;
        htons(bytes, offset, rid);
        offset+=2;
        htons(bytes,offset,status);

        return bytes;
    }

    /**
     *封装一键返航
     * */
    public static byte[] returninfo(int uid,int rid){

        int offset=0;
        int PackLength=Global.SIZE_OF_PACKET_HEAD+2;
        byte[] bytes = new byte[Global.SIZE_OF_PACKET_HEAD+2];
        PacketHead(HEAD_nSN, uid, 2, PackLength,cmdType.CMD_FUNCTION_RETURN,bytes, offset);
        offset+=Global.SIZE_OF_PACKET_HEAD;
        htons(bytes, offset, rid);

        return bytes;
    }
    //-------------------------------------------------------------------------------------------------------

    /**
     * 接收的数据结构头部解析
     */
    public static class ToHead {
        public long nSN;     //0x8eb69af7  4字节   int以上类型接收
        public int nUserID;  //用户ID  2字节 16位  short以上类型接收
        public int nType;    //0 为机器人上位机、1为电脑PC端、2为Android手机端   2字节 16位  short以上类型接收
        public int nPackLength; //总包的长度 不只是head的长度  是全数据的长度 2字节 16位  short以上类型接收
        public int nCmdType; //具体的命令  2字节 16位  short以上类型接收

        public ToHead(byte[] bb, int offset) {

            nSN = Global.ntohl(bb, offset);
            offset += 4;
            nUserID = Global.ntohs(bb, offset);
            offset += 2;
            nType = Global.ntohs(bb, offset);
            offset += 2;
            nPackLength = Global.ntohs(bb, offset);
            offset += 2;
            nCmdType = Global.ntohs(bb, offset);

        }

    }

    /**
     * 解析登录时服务器发送的数据
     */
    public static class URLInfo {

        public String URl;

        public URLInfo(byte[] bb, int offset, int end) {
            URl = Global.getString(bb, offset, end);
        }

    }

    /**
     * 解析机器人信息
     */
    public static class RobotInfo {

        public int nUserID;
        public String cName;
        public String cPassword;
        public String cRealName;
        
        public RobotInfo(byte[] bb, int offset) {
            nUserID = Global.ntohs(bb, offset);
            offset+=2;
            cName = getString(bb, offset, Global.MAX_ACCOUNT_LENGTH);
            offset+=Global.MAX_ACCOUNT_LENGTH;
            cPassword = getString(bb,offset,Global.MAX_ACCOUNT_LENGTH);
            offset+=Global.MAX_ACCOUNT_LENGTH;
            cRealName = getString(bb,offset,Global.MAX_ACCOUNT_LENGTH);
        }
    }

    /**
     * 解析机器人状态
     */
    public static class RobotStatus {
        public int nRobotID;
        public String cRealName;
        public int nStatus;

        public RobotStatus(byte[] bb, int offset){
            nRobotID= Global.ntohs(bb, offset);
            offset+=2;
            cRealName=getString(bb,offset,Global.MAX_ACCOUNT_LENGTH);
            offset+=Global.MAX_ACCOUNT_LENGTH;
            nStatus=Global.ntohs(bb,offset);
        }
    }


    /**
     * 解析机器人参数
     * */
    public static class RobotParam{
        public int nRobotID;
        public int nSpeed;
        public String cPositionX;
        public String cPositionY;
        public int nPower;

        public RobotParam(byte[] bb,int offset){
            nRobotID= Global.ntohs(bb, offset);
            offset+=2;
            nSpeed=Global.ntohs(bb, offset);
            offset+=2;
            cPositionX=getString(bb,offset,10);
            offset+=10;
            cPositionY=getString(bb,offset,10);
            offset+=10;
            nPower=Global.ntohs(bb, offset);
        }

    }

    /**
     * 解析巡检状态 更新巡检状态
     * */
    public static class InspectionInfo{
        public int nRobotID;
        public int nStatus;//0 自动巡检  1 手动巡检
        public InspectionInfo(byte[] bb,int offset){
            nRobotID= Global.ntohs(bb, offset);
            offset+=2;
            nStatus=Global.ntohs(bb, offset);
        }

    }

    /**
     *解析一键返航
     * */
    public static class returnInfo{
        public int nRobotID;

        public returnInfo(byte[] bb,int offset){
            nRobotID= Global.ntohs(bb, offset);
            offset+=2;
        }

    }
}