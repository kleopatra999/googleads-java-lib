// Copyright 2013 Google Inc. All Rights Reserved.
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

package dfp.axis.v201306.lineitemcreativeassociationservice;

import com.google.api.ads.common.lib.auth.OfflineCredentials;
import com.google.api.ads.common.lib.auth.OfflineCredentials.Api;
import com.google.api.ads.dfp.axis.factory.DfpServices;
import com.google.api.ads.dfp.axis.utils.v201306.StatementBuilder;
import com.google.api.ads.dfp.axis.v201306.DeactivateLineItemCreativeAssociations;
import com.google.api.ads.dfp.axis.v201306.LineItemCreativeAssociation;
import com.google.api.ads.dfp.axis.v201306.LineItemCreativeAssociationPage;
import com.google.api.ads.dfp.axis.v201306.LineItemCreativeAssociationServiceInterface;
import com.google.api.ads.dfp.axis.v201306.UpdateResult;
import com.google.api.ads.dfp.lib.client.DfpSession;
import com.google.api.client.auth.oauth2.Credential;

/**
 * This example deactivates all LICAs for a given line item. To determine which
 * LICAs exist, run GetAllLicas.java. To determine which line items
 * exist, run GetAllLineItems.java
 *
 * Credentials and properties in {@code fromFile()} are pulled from the
 * "ads.properties" file. See README for more info.
 *
 * Tags: LineItemCreativeAssociationService.getLineItemCreativeAssociationsByStatement
 * Tags: LineItemCreativeAssociationService.performLineItemCreativeAssociationAction
 *
 * @author Adam Rogal
 */
public class DeactivateLicas {

  // Set the ID of the line item to deactivate LICAs for.
  private static final String LINE_ITEM_ID = "INSERT_LINE_ITEM_ID_HERE";

  public static void runExample(DfpServices dfpServices, DfpSession session, long lineItemId)
      throws Exception {
    // Get the LineItemCreativeAssociationService.
    LineItemCreativeAssociationServiceInterface licaService =
        dfpServices.get(session, LineItemCreativeAssociationServiceInterface.class);

    // Create a statement to select all LICAs for a line item.
    StatementBuilder statementBuilder = new StatementBuilder()
        .where("WHERE lineItemId = :lineItemId")
        .orderBy("lineItemId ASC, creativeId ASC")
        .limit(StatementBuilder.SUGGESTED_PAGE_LIMIT)
        .withBindVariableValue("lineItemId", lineItemId);

    // Default for total result set size.
    int totalResultSetSize = 0;

    do {
      // Get LICAs by statement.
      LineItemCreativeAssociationPage page =
          licaService.getLineItemCreativeAssociationsByStatement(statementBuilder.toStatement());

      if (page.getResults() != null) {
        totalResultSetSize = page.getTotalResultSetSize();
        int i = page.getStartIndex();
        for (LineItemCreativeAssociation lica : page.getResults()) {
          if (lica.getCreativeSetId() != null) {
            System.out.printf("%d) LICA with line item ID \"%d\" and creative "
                + "set ID \"%d\" will be deactivated.\n", i++, lica.getLineItemId(),
                lica.getCreativeSetId());
          } else {
            System.out.printf(
                "%d) LICA with line item ID \"%d\" and creative ID \"%d\" will be deactivated.\n",
                i++, lica.getLineItemId(), lica.getCreativeId());
          }
        }
      }

      statementBuilder.increaseOffsetBy(StatementBuilder.SUGGESTED_PAGE_LIMIT);
    } while (statementBuilder.getOffset() < totalResultSetSize);

    System.out.printf("Number of LICAs to be deactivated: %d\n", totalResultSetSize);

    if (totalResultSetSize > 0) {
      // Remove limit and offset from statement.
      statementBuilder.removeLimitAndOffset();

      // Create action.
      DeactivateLineItemCreativeAssociations action = new DeactivateLineItemCreativeAssociations();

      // Perform action.
      UpdateResult result = licaService.performLineItemCreativeAssociationAction(
          action, statementBuilder.toStatement());

      if (result != null && result.getNumChanges() > 0) {
        System.out.printf("Number of LICAs deactivated: %d\n", result.getNumChanges());
      } else {
        System.out.println("No LICAs were deactivated.");
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // Generate a refreshable OAuth2 credential similar to a ClientLogin token
    // and can be used in place of a service account.
    Credential oAuth2Credential = new OfflineCredentials.Builder()
        .forApi(Api.DFP)
        .fromFile()
        .build()
        .generateCredential();

    // Construct a DfpSession.
    DfpSession session = new DfpSession.Builder()
        .fromFile()
        .withOAuth2Credential(oAuth2Credential)
        .build();

    DfpServices dfpServices = new DfpServices();

    runExample(dfpServices, session, Long.parseLong(LINE_ITEM_ID));
  }
}
