package de.fraunhofer.fit.IDSFAIRApp.model;

import de.fraunhofer.iais.eis.*;
import de.fraunhofer.iais.eis.util.TypedLiteral;
import de.fraunhofer.iais.eis.util.Util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public final class IDSAppResource {

    public static AppResource generateIDSAppResource(){
        var titles = new ArrayList<TypedLiteral>();
        titles.add(new TypedLiteral("IDSSmartDataAppTemplate", String.valueOf(Language.EN)));

        var description = new ArrayList<TypedLiteral>();
        description.add(new TypedLiteral("Example Smart-Data-App for demonstration purposes", String.valueOf(Language.EN)));

        var keywords = new ArrayList<TypedLiteral>();
        keywords.add(new TypedLiteral("Demo", String.valueOf(Language.EN)));
        keywords.add(new TypedLiteral("Example", String.valueOf(Language.EN)));
        keywords.add(new TypedLiteral("Smart-Data-App", String.valueOf(Language.EN)));

        var endPointInformation = new ArrayList<TypedLiteral>();
        endPointInformation.add(new TypedLiteral("This is the default endpoint of the Fraunhofer FIT AppStore", String.valueOf(Language.EN)));

        var endPointDocumentation = new ArrayList<URI>();
        endPointDocumentation.add(URI.create("http://appstore.fit.fraunhofer.de/app/appId"));

        SmartDataApp dataAppInformation = new SmartDataAppBuilder()
                ._appDocumentation_("Place for an app-related human-readable documentation")
                ._appEnvironmentVariables_("$dbUser=sa;$dpPasswd=passwd")
                ._appStorageConfiguration_("-v /data")
                ._appEndpoint_(Util.asList(
                        new AppEndpointBuilder()
                                ._endpointInformation_(Util.asList(new TypedLiteral("Endpoint for app input data", String.valueOf(Language.EN))))
                                ._endpointDocumentation_(Util.asList(URI.create("https://app.swaggerhub.com/apis/app/1337")))
                                ._appEndpointType_(AppEndpointType.INPUT_ENDPOINT)
                                ._appEndpointPort_(BigInteger.valueOf(8080))
                                ._path_("/input")
                                ._appEndpointMediaType_(new IANAMediaTypeBuilder()._filenameExtension_("application/json").build())
                                ._appEndpointProtocol_("HTTP/1.1")
                                .build(),
                        new AppEndpointBuilder()
                                ._endpointInformation_(Util.asList(new TypedLiteral("Endpoint for app output data", String.valueOf(Language.EN))))
                                ._endpointDocumentation_(Util.asList(URI.create("https://app.swaggerhub.com/apis/app/1337")))
                                ._appEndpointType_(AppEndpointType.OUTPUT_ENDPOINT)
                                ._appEndpointPort_(BigInteger.valueOf(8080))
                                ._path_("/output")
                                ._appEndpointMediaType_(new IANAMediaTypeBuilder()._filenameExtension_("application/json").build())
                                ._appEndpointProtocol_("HTTP/1.1")
                                .build(),
                        new AppEndpointBuilder()
                                ._endpointInformation_(Util.asList(new TypedLiteral("Endpoint for app configuration / parameterization", String.valueOf(Language.EN))))
                                ._endpointDocumentation_(Util.asList(URI.create("https://app.swaggerhub.com/apis/app/1337")))
                                ._appEndpointType_(AppEndpointType.CONFIG_ENDPOINT)
                                ._appEndpointPort_(BigInteger.valueOf(8080))
                                ._path_("/config")
                                ._appEndpointMediaType_(new IANAMediaTypeBuilder()._filenameExtension_("application/json").build())
                                ._appEndpointProtocol_("HTTP/1.1")
                                .build(),
                        new AppEndpointBuilder()
                                ._endpointInformation_(Util.asList(new TypedLiteral("Endpoint for app status information", String.valueOf(Language.EN))))
                                ._endpointDocumentation_(Util.asList(URI.create("https://app.swaggerhub.com/apis/app/1337")))
                                ._appEndpointType_(AppEndpointType.STATUS_ENDPOINT)
                                ._appEndpointPort_(BigInteger.valueOf(8080))
                                ._path_("/status")
                                ._appEndpointMediaType_(new IANAMediaTypeBuilder()._filenameExtension_("application/json").build())
                                ._appEndpointProtocol_("HTTP/1.1")
                                .build(),
                        new AppEndpointBuilder()
                                ._endpointInformation_(Util.asList(new TypedLiteral("Endpoint for app processing", String.valueOf(Language.EN))))
                                ._endpointDocumentation_(Util.asList(URI.create("https://app.swaggerhub.com/apis/app/1337")))
                                ._appEndpointType_(AppEndpointType.PROCESS_ENDPOINT)
                                ._appEndpointPort_(BigInteger.valueOf(8080))
                                ._path_("/process")
                                ._appEndpointMediaType_(new IANAMediaTypeBuilder()._filenameExtension_("application/json").build())
                                ._appEndpointProtocol_("HTTP/1.1")
                                .build(),
                        new AppEndpointBuilder()
                                ._endpointInformation_(Util.asList(new TypedLiteral("Endpoint for app usage policies", String.valueOf(Language.EN))))
                                ._endpointDocumentation_(Util.asList(URI.create("https://app.swaggerhub.com/apis/app/1337")))
                                ._appEndpointType_(AppEndpointType.USAGE_POLICY_ENDPOINT)
                                ._appEndpointPort_(BigInteger.valueOf(8080))
                                ._path_("/usage")
                                ._appEndpointMediaType_(new IANAMediaTypeBuilder()._filenameExtension_("application/json").build())
                                ._appEndpointProtocol_("HTTP/1.1")
                                .build()
                        )
                ).build();

        AppRepresentation appRepresentation = new AppRepresentationBuilder()
                ._mediaType_(new IANAMediaTypeBuilder()._filenameExtension_("application/zip").build())
                ._dataAppDistributionService_(URI.create("https://appstore.fit.fraunhofer.de/registry"))
                ._dataAppRuntimeEnvironment_("Docker")
                ._dataAppInformation_(dataAppInformation)
                .build();

        //
        AppResource appResource = new AppResourceBuilder(URI.create("http://appstore.fit.fraunhofer.de/apps/ExampleApp/v01"))
                ._title_(titles)
                ._description_(description)
                ._keyword_(keywords)
                ._customLicense_(URI.create("https://www.apache.org/licenses/LICENSE-2.0"))
                ._standardLicense_(URI.create("https://www.apache.org/licenses/LICENSE-2.0"))
                ._created_(getXMLGregorianCalendarNow())
                ._modified_(getXMLGregorianCalendarNow())
                ._version_("1.0.0")
                ._resourceEndpoint_(Util.asList(new ConnectorEndpointBuilder()
                        ._accessURL_(URI.create("https://appstore.fit.fraunhofer.de"))
                        ._path_("/")
                        ._endpointInformation_(endPointInformation)
                        ._endpointDocumentation_(endPointDocumentation)
                        .build())
                )
                ._representation_(Util.asList(appRepresentation))
                .build();

        return appResource;
    }

    private static XMLGregorianCalendar getXMLGregorianCalendarNow()
    {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            XMLGregorianCalendar now =
                    datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
            return now;
        } catch (DatatypeConfigurationException ex){
            return null;
        }
    }

}

