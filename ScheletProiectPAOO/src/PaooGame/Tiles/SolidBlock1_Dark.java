package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class GrassTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class SolidBlock1_Dark extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public SolidBlock1_Dark(int id)
    {
            /// Apel al constructorului clasei de baza
        super(Assets.solidBlock1_dark, id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
