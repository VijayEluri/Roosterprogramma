/*********************************************************************
*
*      Copyright (C) 2006 Andrew Khan
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
***************************************************************************/

package excel.biff.drawing;

class Chunk
{
  private int pos;
  private int length;
  private ChunkType type;
  private byte[] data;

  public Chunk(int p, int l, ChunkType ct, byte[] d)
  {
    pos = p;
    length = l;
    type = ct;
    data = new byte[length];
    System.arraycopy(d, pos, data, 0, length);
    
  }

  public byte[] getData()
  {
    return data;
  }
}
