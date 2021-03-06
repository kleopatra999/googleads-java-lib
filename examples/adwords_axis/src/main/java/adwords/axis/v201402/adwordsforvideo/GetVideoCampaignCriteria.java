// Copyright 2014 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package adwords.axis.v201402.adwordsforvideo;

import com.google.api.ads.adwords.axis.factory.AdWordsServices;
import com.google.api.ads.adwords.axis.v201402.cm.Paging;
import com.google.api.ads.adwords.axis.v201402.video.NegativeVideoCampaignCriterion;
import com.google.api.ads.adwords.axis.v201402.video.VideoCampaignCriterion;
import com.google.api.ads.adwords.axis.v201402.video.VideoCampaignCriterionPage;
import com.google.api.ads.adwords.axis.v201402.video.VideoCampaignCriterionSelector;
import com.google.api.ads.adwords.axis.v201402.video.VideoCampaignCriterionServiceInterface;
import com.google.api.ads.adwords.axis.v201402.video.VideoCampaignStatus;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.common.lib.auth.OfflineCredentials;
import com.google.api.ads.common.lib.auth.OfflineCredentials.Api;
import com.google.api.client.auth.oauth2.Credential;

/**
 * This example demonstrates how to retrieve all campaign-level criteria in a
 * video campaign.
 * 
 * Note: AdWords for Video API is a Beta feature.
 *
 * Credentials and properties in {@code fromFile()} are pulled from the
 * "ads.properties" file. See README for more info.
 *
 * Tags: VideoCampaignCriterionService.get
 *
 * Category: adx-exclude
 *
 * @author Kevin Winter
 * @author Ray Tsang
 */
public class GetVideoCampaignCriteria {

  private static final int PAGE_SIZE = 100;

  public static void main(String[] args) throws Exception {
    // Generate a refreshable OAuth2 credential similar to a ClientLogin token
    // and can be used in place of a service account.
    Credential oAuth2Credential = new OfflineCredentials.Builder()
        .forApi(Api.ADWORDS)
        .fromFile()
        .build()
        .generateCredential();

    // Construct an AdWordsSession.
    AdWordsSession session = new AdWordsSession.Builder()
        .fromFile()
        .withOAuth2Credential(oAuth2Credential)
        .build();

    Long campaignId = Long.valueOf("INSERT_CAMPAIGN_ID_HERE");

    AdWordsServices adWordsServices = new AdWordsServices();

    runExample(adWordsServices, session, campaignId);
  }

  public static void runExample(
      AdWordsServices adWordsServices, AdWordsSession session, Long campaignId)
          throws Exception {
    // Get the VideoCampaignCriterionService.
    VideoCampaignCriterionServiceInterface videoCampaignCriterionService =
        adWordsServices.get(session, VideoCampaignCriterionServiceInterface.class);

    int offset = 0;

    // Create selector.
    VideoCampaignCriterionSelector selector = new VideoCampaignCriterionSelector();
    selector.setCampaignIds(new long[] {campaignId});
    // Select only from active campaigns.
    selector.setCampaignStatuses(
        new VideoCampaignStatus[] {VideoCampaignStatus.ACTIVE});
    selector.setPaging(new Paging(offset, PAGE_SIZE));

    VideoCampaignCriterionPage page;
    do {
      // Get all criteria for the campaign.
      page = videoCampaignCriterionService.get(selector);

      // Display criteria.
      if (page.getEntries() != null) {
        for (VideoCampaignCriterion videoCampaignCriterion : page.getEntries()) {
          String negative =
              (videoCampaignCriterion instanceof NegativeVideoCampaignCriterion) ?
                  " (negative)" : "";
          System.out.printf("Video%s criterion ID '%d' of type '%s' was found.%n",
              negative,
              videoCampaignCriterion.getCriterion().getId(),
              videoCampaignCriterion.getCriterion().getBaseCriterionType());
        }
      } else {
        System.out.println("No criteria were found.");
      }

      offset += PAGE_SIZE;
      selector.getPaging().setStartIndex(offset);
    } while (offset < page.getTotalNumEntries());
  }
}
