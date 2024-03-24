package icbm.gangshao.terminal;

import java.util.List;

import icbm.gangshao.ISpecialAccess;
import icbm.gangshao.shimian.IScroll;

public interface ITerminal extends ISpecialAccess, IScroll {
    List<String> getTerminalOuput();

    boolean addToConsole(final String p0);
}
