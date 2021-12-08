package com.demo.relationalGeode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Region(name="some-object-region")
public class SomeObject {
    @Id
    private String someId;
    private List<CustomFirstObject> customFirstObjects;
}
