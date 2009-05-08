/* 
 *
 * SchemaCrawler
 * http://sourceforge.net/projects/schemacrawler
 * Copyright (c) 2000-2009, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */

package schemacrawler.main;


import schemacrawler.schemacrawler.Config;
import schemacrawler.schemacrawler.InclusionRule;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import sf.util.CommandLineParser.BooleanOption;
import sf.util.CommandLineParser.Option;
import sf.util.CommandLineParser.StringOption;

/**
 * Parses the command line.
 * 
 * @author Sualeh Fatehi
 */
final class SchemaCrawlerOptionsParser
  extends BaseOptionsParser<SchemaCrawlerOptions>
{

  private final StringOption optionSchemaPattern = new StringOption(Option.NO_SHORT_FORM,
                                                                    "schemapattern",
                                                                    "");
  private final BooleanOption optionShowStoredProcedures = new BooleanOption(Option.NO_SHORT_FORM,
                                                                             "show_stored_procedures");
  private final StringOption optionTableTypes = new StringOption(Option.NO_SHORT_FORM,
                                                                 "table_types",
                                                                 SchemaCrawlerOptions.DEFAULT_TABLE_TYPES);

  private final StringOption optionTables = new StringOption(Option.NO_SHORT_FORM,
                                                             "tables",
                                                             InclusionRule.ALL);
  private final StringOption optionExcludeColumns = new StringOption(Option.NO_SHORT_FORM,
                                                                     "excludecolumns",
                                                                     InclusionRule.NONE);

  private final StringOption optionProcedures = new StringOption(Option.NO_SHORT_FORM,
                                                                 "procedures",
                                                                 InclusionRule.ALL);
  private final StringOption optionExcludeProcedureColumns = new StringOption(Option.NO_SHORT_FORM,
                                                                              "excludeinout",
                                                                              InclusionRule.NONE);

  private final StringOption optionGrepColumns = new StringOption(Option.NO_SHORT_FORM,
                                                                  "grepcolumns",
                                                                  InclusionRule.NONE);
  private final StringOption optionGrepProcedureColumns = new StringOption(Option.NO_SHORT_FORM,
                                                                           "grepinout",
                                                                           InclusionRule.NONE);
  private final BooleanOption optionGrepInvertMatch = new BooleanOption('v',
                                                                        "invert-match");

  private final BooleanOption optionSortColumns = new BooleanOption(Option.NO_SHORT_FORM,
                                                                    "sortcolumns");
  private final BooleanOption optionSortInout = new BooleanOption(Option.NO_SHORT_FORM,
                                                                  "sortinout");

  private final SchemaCrawlerOptions options;

  SchemaCrawlerOptionsParser(final String[] args,
                             final Config config,
                             final String partition)
  {
    super(args);
    options = new SchemaCrawlerOptions(config, partition);
  }

  @Override
  protected String getHelpResource()
  {
    return "/help/SchemaCrawlerOptions.readme.txt";
  }

  @Override
  protected SchemaCrawlerOptions getOptions()
  {
    parse(new Option[] {
        optionSchemaPattern,
        optionTableTypes,
        optionShowStoredProcedures,
        optionTables,
        optionExcludeColumns,
        optionProcedures,
        optionExcludeProcedureColumns,
        optionGrepColumns,
        optionGrepProcedureColumns,
        optionGrepInvertMatch,
        optionSortColumns,
        optionSortInout,
    });

    if (optionTableTypes.isFound())
    {
      options.setTableTypes(optionTableTypes.getValue());
    }
    if (optionShowStoredProcedures.isFound())
    {
      options.setShowStoredProcedures(optionShowStoredProcedures.getValue());
    }

    if (optionTables.isFound())
    {
      final InclusionRule tableInclusionRule = new InclusionRule(optionTables
        .getValue(), InclusionRule.NONE);
      options.setTableInclusionRule(tableInclusionRule);
    }
    if (optionExcludeColumns.isFound())
    {
      final InclusionRule columnInclusionRule = new InclusionRule(InclusionRule.ALL,
                                                                  optionExcludeColumns
                                                                    .getValue());
      options.setColumnInclusionRule(columnInclusionRule);
    }

    if (optionProcedures.isFound())
    {
      final InclusionRule procedureInclusionRule = new InclusionRule(optionProcedures
                                                                       .getValue(),
                                                                     InclusionRule.NONE);
      options.setProcedureInclusionRule(procedureInclusionRule);
    }
    if (optionExcludeProcedureColumns.isFound())
    {
      final InclusionRule procedureColumnInclusionRule = new InclusionRule(InclusionRule.ALL,
                                                                           optionExcludeProcedureColumns
                                                                             .getValue());
      options.setProcedureColumnInclusionRule(procedureColumnInclusionRule);
    }

    if (optionGrepInvertMatch.isFound())
    {
      options.setGrepInvertMatch(optionGrepInvertMatch.getValue());
    }

    if (optionGrepColumns.isFound())
    {
      final InclusionRule grepColumnInclusionRule = new InclusionRule(optionGrepColumns
                                                                        .getValue(),
                                                                      InclusionRule.NONE);
      options.setGrepColumnInclusionRule(grepColumnInclusionRule);
    }

    if (optionGrepProcedureColumns.isFound())
    {
      final InclusionRule grepProcedureColumnInclusionRule = new InclusionRule(optionGrepProcedureColumns
                                                                                 .getValue(),
                                                                               InclusionRule.NONE);
      options
        .setGrepProcedureColumnInclusionRule(grepProcedureColumnInclusionRule);
    }

    if (optionSortColumns.isFound())
    {
      options.setAlphabeticalSortForTableColumns(optionSortColumns.getValue());
    }
    if (optionSortInout.isFound())
    {
      options
        .setAlphabeticalSortForProcedureColumns(optionSortInout.getValue());
    }

    return options;
  }

}
