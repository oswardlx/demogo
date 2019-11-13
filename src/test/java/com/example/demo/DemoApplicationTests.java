package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.example.util.test.PrePdf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private PrePdf prePdf;

    @Test
    public void contextLoads() throws Exception {
//        String testJson = "[{\"check_Focus\":false,\"pageSize\":\"A4\",\"marginRight\":\"16\",\"children\":[{\"check_Focus\":false,\"parentTId\":\"treeDemo_1\",\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":11,\"tId\":\"treeDemo_3\",\"ename\":\"Cell\",\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_3\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":111,\"tId\":\"treeDemo_4\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_4\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":11111,\"zAsync\":true,\"text\":\"3333\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1111,\"fontStyle\":0,\"tId\":\"treeDemo_5\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":1111,\"zAsync\":true,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"name\":\"单元格\",\"checked\":false,\"isLastNode\":false,\"halfCheck\":false,\"check_Child_State\":0,\"id\":111,\"zAsync\":true,\"nocheck\":false,\"open\":true},{\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"paddingRight\":16,\"cellHeight\":15,\"paddingBottom\":16,\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_6\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":112,\"tId\":\"treeDemo_8\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_8\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":101,\"zAsync\":true,\"text\":\"ewwewr\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":101,\"fontStyle\":0,\"tId\":\"treeDemo_9\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":101,\"zAsync\":false,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"checked\":false,\"id\":112,\"zAsync\":false,\"paddingTop\":16,\"nocheck\":false,\"isFirstNode\":false,\"chkDisabled\":false,\"rowSpan\":1,\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"colSpan\":1,\"checkedOld\":false,\"rotation\":0,\"pId\":11,\"tId\":\"treeDemo_6\",\"ename\":\"Cell\",\"horizontalAlignment\":\"0\",\"name\":\"单元格\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"paddingLeft\":16,\"open\":true,\"verticalAlignment\":\"6\"}],\"editNameFlag\":false,\"checked\":false,\"id\":11,\"zAsync\":true,\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":true,\"isHover\":true,\"level\":1,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1,\"colsRadioArr\":[12,23],\"numCols\":2,\"tId\":\"treeDemo_2\",\"ename\":\"Table\",\"widthRadio\":100,\"name\":\"表格\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"open\":true}],\"editNameFlag\":false,\"checked\":false,\"id\":1,\"zAsync\":true,\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":true,\"isHover\":false,\"level\":0,\"isAjaxing\":false,\"checkedOld\":false,\"colsRadioArr\":[],\"tId\":\"treeDemo_1\",\"marginLeft\":\"16\",\"ename\":\"Page\",\"pageRotate\":\"vertical\",\"name\":\"纸张与文档\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"marginBottom\":\"16\",\"open\":true,\"marginTop\":\"16\"},{\"check_Focus\":false,\"parentTId\":\"treeDemo_1\",\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":11,\"tId\":\"treeDemo_3\",\"ename\":\"Cell\",\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_3\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":111,\"tId\":\"treeDemo_4\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_4\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":11111,\"zAsync\":true,\"text\":\"3333\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1111,\"fontStyle\":0,\"tId\":\"treeDemo_5\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":1111,\"zAsync\":true,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"name\":\"单元格\",\"checked\":false,\"isLastNode\":false,\"halfCheck\":false,\"check_Child_State\":0,\"id\":111,\"zAsync\":true,\"nocheck\":false,\"open\":true},{\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"paddingRight\":16,\"cellHeight\":15,\"paddingBottom\":16,\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_6\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":112,\"tId\":\"treeDemo_8\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_8\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":101,\"zAsync\":true,\"text\":\"ewwewr\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":101,\"fontStyle\":0,\"tId\":\"treeDemo_9\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":101,\"zAsync\":false,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"checked\":false,\"id\":112,\"zAsync\":false,\"paddingTop\":16,\"nocheck\":false,\"isFirstNode\":false,\"chkDisabled\":false,\"rowSpan\":1,\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"colSpan\":1,\"checkedOld\":false,\"rotation\":0,\"pId\":11,\"tId\":\"treeDemo_6\",\"ename\":\"Cell\",\"horizontalAlignment\":\"0\",\"name\":\"单元格\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"paddingLeft\":16,\"open\":true,\"verticalAlignment\":\"6\"}],\"editNameFlag\":false,\"checked\":false,\"id\":11,\"zAsync\":true,\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":true,\"isHover\":true,\"level\":1,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1,\"colsRadioArr\":[12,23],\"numCols\":2,\"tId\":\"treeDemo_2\",\"ename\":\"Table\",\"widthRadio\":100,\"name\":\"表格\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"open\":true},{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":11,\"tId\":\"treeDemo_3\",\"ename\":\"Cell\",\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_3\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":111,\"tId\":\"treeDemo_4\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_4\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":11111,\"zAsync\":true,\"text\":\"3333\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1111,\"fontStyle\":0,\"tId\":\"treeDemo_5\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":1111,\"zAsync\":true,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"name\":\"单元格\",\"checked\":false,\"isLastNode\":false,\"halfCheck\":false,\"check_Child_State\":0,\"id\":111,\"zAsync\":true,\"nocheck\":false,\"open\":true},{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_3\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":111,\"tId\":\"treeDemo_4\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_4\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":11111,\"zAsync\":true,\"text\":\"3333\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1111,\"fontStyle\":0,\"tId\":\"treeDemo_5\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":1111,\"zAsync\":true,\"nocheck\":false,\"open\":true},{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_4\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":11111,\"zAsync\":true,\"text\":\"3333\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":1111,\"fontStyle\":0,\"tId\":\"treeDemo_5\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false},{\"check_Focus\":false,\"parentTId\":\"treeDemo_2\",\"paddingRight\":16,\"cellHeight\":15,\"paddingBottom\":16,\"children\":[{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_6\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":112,\"tId\":\"treeDemo_8\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_8\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":101,\"zAsync\":true,\"text\":\"ewwewr\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":101,\"fontStyle\":0,\"tId\":\"treeDemo_9\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":101,\"zAsync\":false,\"nocheck\":false,\"open\":true}],\"editNameFlag\":false,\"checked\":false,\"id\":112,\"zAsync\":false,\"paddingTop\":16,\"nocheck\":false,\"isFirstNode\":false,\"chkDisabled\":false,\"rowSpan\":1,\"isParent\":true,\"isHover\":false,\"level\":2,\"isAjaxing\":false,\"colSpan\":1,\"checkedOld\":false,\"rotation\":0,\"pId\":11,\"tId\":\"treeDemo_6\",\"ename\":\"Cell\",\"horizontalAlignment\":\"0\",\"name\":\"单元格\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"paddingLeft\":16,\"open\":true,\"verticalAlignment\":\"6\"},{\"isFirstNode\":true,\"chkDisabled\":false,\"check_Focus\":false,\"isParent\":true,\"parentTId\":\"treeDemo_6\",\"isHover\":false,\"level\":3,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":112,\"tId\":\"treeDemo_8\",\"ename\":\"Paragraph\",\"children\":[{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_8\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":101,\"zAsync\":true,\"text\":\"ewwewr\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":101,\"fontStyle\":0,\"tId\":\"treeDemo_9\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}],\"editNameFlag\":false,\"name\":\"段落\",\"checked\":false,\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":0,\"id\":101,\"zAsync\":false,\"nocheck\":false,\"open\":true},{\"check_Focus\":false,\"fontBase\":\"\",\"parentTId\":\"treeDemo_8\",\"editNameFlag\":false,\"parameter\":\"{}\",\"checked\":false,\"id\":101,\"zAsync\":true,\"text\":\"ewwewr\",\"nocheck\":false,\"isFirstNode\":true,\"chkDisabled\":false,\"isParent\":false,\"isHover\":false,\"level\":4,\"isAjaxing\":false,\"checkedOld\":false,\"pId\":101,\"fontStyle\":0,\"tId\":\"treeDemo_9\",\"ename\":\"Chunk\",\"name\":\"块\",\"isLastNode\":true,\"halfCheck\":false,\"check_Child_State\":-1,\"fontSize\":9,\"open\":false}]";
//        JSONArray jo = JSONArray.parseArray(testJson);
//        prePdf.decompling(jo);
        prePdf.initLoop();
    }


}
