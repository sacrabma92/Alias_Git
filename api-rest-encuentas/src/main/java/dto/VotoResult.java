
package dto;

import java.util.Collection;
import lombok.Data;

@Data
public class VotoResult {
    private int totlaVotos;
    private Collection<OpcionCount> results;
}
