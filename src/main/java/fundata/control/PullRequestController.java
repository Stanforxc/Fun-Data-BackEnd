package fundata.control;

import fundata.model.PullRequest;
import fundata.service.PullRequestService;
import fundata.service.QiniuService;
import fundata.viewmodel.PullRequestView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by ocean on 16-12-6.
 */
@RestController
public class PullRequestController {
    @Autowired
    private PullRequestService pullRequestService;

    @Autowired
    private QiniuService qiniuService;

    @RequestMapping(value = "/getPullRequest", method = RequestMethod.POST)
    public PullRequestView getPullRequest(@RequestParam(name = "datasetname") String datasetName,
                                          @RequestParam(name = "page") int page) {
        Set<PullRequest> pullRequests = pullRequestService.findByDatasetName(datasetName);
        if (pullRequests != null) {
            return new PullRequestView(pullRequests);
        } else {
            return new PullRequestView(0);
        }
    }

    @RequestMapping(value = "/newPullRequest", method = RequestMethod.POST)
    public void newPullRequest(@RequestParam(name = "datasetname") String datasetname,
                               @RequestParam(name = "username") String username) {
        

    }

}