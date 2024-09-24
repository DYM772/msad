<template>

    <v-data-table
        :headers="headers"
        :items="adStatus"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'AdStatusView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "materialId", value: "materialId" },
                { text: "materialName", value: "materialName" },
                { text: "materialUrl", value: "materialUrl" },
                { text: "adId", value: "adId" },
                { text: "adStatus", value: "adStatus" },
                { text: "title", value: "title" },
                { text: "content", value: "content" },
                { text: "budget", value: "budget" },
                { text: "target", value: "target" },
                { text: "targetImpressions", value: "targetImpressions" },
                { text: "deliveryStatus", value: "deliveryStatus" },
                { text: "startDate", value: "startDate" },
                { text: "endDate", value: "endDate" },
            ],
            adStatus : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/adStatuses'))

            temp.data._embedded.adStatuses.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.adStatus = temp.data._embedded.adStatuses;
        },
        methods: {
        }
    }
</script>

