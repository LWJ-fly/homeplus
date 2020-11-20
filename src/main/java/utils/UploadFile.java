package utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: homeplus
 * @description: 上传文件的工具类
 * @author: ZEK
 * @create: 2019-04-15 18:27
 **/
public final class UploadFile {

    /**
     * 上传文件的方法
     * @param request
     * @param uploadFile
     * @return 文件的名字
     * @throws IOException IO 异常
     */
    public static String uploadFile(HttpServletRequest request, MultipartFile uploadFile) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());
        //uploads文件夹位置
        String rootPath = request.getServletContext().getRealPath("/upload");
        System.out.println(rootPath);
        //原始名称
        String originalFilename = uploadFile.getOriginalFilename();
        //新的文件名称
        String newFileName = res + originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println(newFileName);
        //新文件
        File newFile = new File(rootPath + File.separator + newFileName);
        //判断目标文件所在的目录是否存在
        if (!newFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        System.out.println(newFile.getName());
        //将内存中的数据写入磁盘
        uploadFile.transferTo(newFile);
        String fileUrl = "/upload/" + newFileName;
        return fileUrl;
    }
}
