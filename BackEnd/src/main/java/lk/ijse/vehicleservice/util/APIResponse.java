package lk.ijse.vehicleservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class APIResponse<T> {
    private int code;
    private String message;
    private Object data;
}
