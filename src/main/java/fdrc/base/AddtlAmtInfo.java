package fdrc.base;

import java.math.BigDecimal;

public class AddtlAmtInfo {
    /* An identifier used to indicate whether or not the
     * terminal/software can support partial authorization approvals.  */
    public String partAuthrztnApprvlCapablt;
    public BigDecimal firstAuthAmt;
    public BigDecimal totalAuthAmt;
}
