package fundata.control;

import fundata.model.Dataer;
import fundata.model.Dataset;
import fundata.model.DatasetTitle;
import fundata.model.Step;
import fundata.service.DataerService;
import fundata.service.DatasetService;
import fundata.viewmodel.DatasetContent;
import fundata.viewmodel.MyDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Set;

/**
 * Created by ocean on 16-12-1.
 */
@RestController
public class DatasetController {
    @Autowired
    @Qualifier("datasetServiceImpl")
    private DatasetService datasetService;

    @Autowired
    private DataerService dataerService;

    @RequestMapping("/createDataset")
    public void createDataset(@RequestParam(value = "username") String username,
                              @RequestParam(value = "datasetname") String datasetname) {
        datasetService.addDataset(username, datasetname);
    }

    @RequestMapping("/getMyDataset")
    public MyDataset getMyDataset(@RequestParam(value = "username") String username) {
        Set<Dataset> datasetList;
        datasetList = datasetService.findByUserName(username);
        MyDataset myDataset = new MyDataset(datasetList);
        return myDataset;
    }

    @RequestMapping("/datasetFileConfirm")
    public void confirmDatasetTitleDesc(@RequestParam(value = "status") Integer status) {

    }

    @RequestMapping("/getDemoContent")
    public DatasetContent getDatasetTitle(@RequestParam(value = "datasetname") String datesetName,
                                          @RequestParam(value = "page") Integer page,
                                          @RequestParam(value = "username") String username) {
        DatasetContent datasetContent = new DatasetContent();

        Dataset dataset = datasetService.findByDatasetName(datesetName);
        Set<Dataer> dataers = dataset.getDataers();
        Dataer dataer = dataerService.findByDataerName(username);
        if (dataers.contains(dataer)) {
            datasetContent.setAdmin(1);
        } else {
            datasetContent.setAdmin(0);
        }
        datasetContent.setDescription(dataset.getDescription());

        Set<DatasetTitle> datasetTitles = dataset.getDatasetTitles();
        for (DatasetTitle datasetTitle : datasetTitles) {

        }
        return null;
    }
}