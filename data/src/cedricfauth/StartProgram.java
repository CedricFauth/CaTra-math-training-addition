/*
Copyright © 2017 Cedric Fauth

    StartProgram.java is part of CaTra.

    CaTra is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    CaTra is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CaTra.  If not, see <http://www.gnu.org/licenses/>.

*/

package cedricfauth;

import java.awt.EventQueue;

public class StartProgram {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Swing1 root = new Swing1();
				root.setVisible(true);
			}
		});
	}

}
