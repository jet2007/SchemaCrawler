/*
========================================================================
SchemaCrawler
http://www.schemacrawler.com
Copyright (c) 2000-2017, Sualeh Fatehi <sualeh@hotmail.com>.
All rights reserved.
------------------------------------------------------------------------

SchemaCrawler is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

SchemaCrawler and the accompanying materials are made available under
the terms of the Eclipse Public License v1.0, GNU General Public License
v3 or GNU Lesser General Public License v3.

You may elect to redistribute this code under any of these licenses.

The Eclipse Public License is available at:
http://www.eclipse.org/legal/epl-v10.html

The GNU General Public License v3 and the GNU Lesser General Public
License v3 are available at:
http://www.gnu.org/licenses/

========================================================================
*/
package schemacrawler.schemacrawler;


import static schemacrawler.schemacrawler.InformationSchemaKeyType.ADDITIONAL_INFO;
import static schemacrawler.schemacrawler.InformationSchemaKeyType.DATABASE_METADATA;
import static schemacrawler.schemacrawler.InformationSchemaKeyType.INFORMATION_SCHEMA;
import static schemacrawler.schemacrawler.InformationSchemaKeyType.METADATA_EXTENSION;

public enum InformationSchemaKey
{

 ADDITIONAL_COLUMN_ATTRIBUTES(ADDITIONAL_INFO),
 ADDITIONAL_TABLE_ATTRIBUTES(ADDITIONAL_INFO),
 CONSTRAINT_COLUMN_USAGE(INFORMATION_SCHEMA),
 EXT_FOREIGN_KEYS(METADATA_EXTENSION),
 EXT_HIDDEN_TABLE_COLUMNS(METADATA_EXTENSION),
 EXT_INDEXES(METADATA_EXTENSION),
 EXT_INDEX_COLUMNS(METADATA_EXTENSION),
 EXT_PRIMARY_KEYS(METADATA_EXTENSION),
 EXT_SYNONYMS(METADATA_EXTENSION),
 EXT_TABLES(METADATA_EXTENSION),
 EXT_TABLE_CONSTRAINTS(METADATA_EXTENSION),
 FOREIGN_KEYS(DATABASE_METADATA),
 INDEXES(DATABASE_METADATA),
 OVERRIDE_TYPE_INFO(DATABASE_METADATA),
 PRIMARY_KEYS(DATABASE_METADATA),
 ROUTINES(INFORMATION_SCHEMA),
 SCHEMATA(INFORMATION_SCHEMA),
 SEQUENCES(INFORMATION_SCHEMA),
 TABLES(DATABASE_METADATA),
 TABLE_COLUMNS(DATABASE_METADATA),
 TABLE_CONSTRAINTS(INFORMATION_SCHEMA),
 TRIGGERS(INFORMATION_SCHEMA),
 VIEWS(INFORMATION_SCHEMA),;

  private final String lookupKey;

  private final InformationSchemaKeyType type;

  private InformationSchemaKey(final InformationSchemaKeyType type)
  {
    this.type = type;
    lookupKey = String.format("select.%s.%s", type.name(), name());
  }

  public String getLookupKey()
  {
    return lookupKey;
  }

  public String getResource()
  {
    return name() + ".sql";
  }

  /**
   * @return the type
   */
  public InformationSchemaKeyType getType()
  {
    return type;
  }

}
